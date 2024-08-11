package io.adabox.client;

import io.adabox.model.base.Request;
import io.adabox.model.base.iface.LocalChainSync;
import io.adabox.model.base.iface.LocalStateQuery;
import io.adabox.model.base.iface.LocalTxSubmission;
import io.adabox.model.chain.response.AcquireResponse;
import io.adabox.model.chain.response.RequestNextResponse;
import io.adabox.model.query.request.*;
import io.adabox.model.query.response.*;
import io.adabox.model.query.response.models.Bound;
import io.adabox.model.query.response.models.EraSummary;
import io.adabox.model.query.response.models.Point;
import io.adabox.model.query.response.models.Utxo;
import io.adabox.model.tx.response.EvaluateTxResponse;
import io.adabox.model.tx.response.SubmitTxResponse;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class OgmiosWSClient extends WebSocketClient implements LocalTxSubmission, LocalStateQuery, LocalChainSync {

    private static final long TIMEOUT = 60; // Sec
    private final AtomicLong msgId = new AtomicLong(0L);
    private final ConcurrentHashMap<Long, BlockingQueue<OgmiosResponse<?>>> blockingQueueConcurrentHashMap = new ConcurrentHashMap<>();

    public OgmiosWSClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("Connection Established!");
        log.debug("onOpen -> ServerHandshake: {}", serverHandshake);
    }

    @Override
    public void onMessage(String message) {
        log.info("Received: {}", message);
        OgmiosResponse<?> response = OgmiosResponse.deserialize(message);
        if (response == null) {
            log.error("Response is Null");
            return;
        }
//        if (response.getFault() != null) {
//            log.error("Error: {}", ((Error) response).getErrorMsg());
//            return;
//        }
        if (blockingQueueConcurrentHashMap.get(Long.valueOf(response.id)).offer(response) && log.isDebugEnabled()) {
            log.debug("Message Offered: {}", message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Connection closed by {}, Code: {}{}", (remote ? "remote peer" : "client"), code,
                (reason == null || reason.isEmpty()) ? reason : ", Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error(ex.getMessage());
        // if the error is fatal then onClose will be called additionally
    }

    private OgmiosResponse<?> send(Request request) {
        OgmiosResponse<?> queryResponse = null;
        long msgIdentifier = msgId.incrementAndGet();
        request.setMsgId(msgIdentifier);
        send(request.toString());
        BlockingQueue<OgmiosResponse<?>> messageBlockingQueue = new ArrayBlockingQueue<>(1);
        blockingQueueConcurrentHashMap.put(msgIdentifier, messageBlockingQueue);
        try {
            queryResponse = messageBlockingQueue.poll(TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return queryResponse;
    }

    @Override
    public AcquireResponse acquire(String point) {
        return null;
    }

    @Override
    public RequestNextResponse requestNext() {
//        send(new RequestNext(msgId.incrementAndGet()).toString());
        return null;
    }

    /* LocalTxSubmission */

    @Override
    public SubmitTxResponse submitTx(byte[] cborData) throws InvalidParameterException {
        if (cborData.length == 0) {
            throw new InvalidParameterException();
        }
//        return (SubmitTxResponse) send(new SubmitTxRequest(cborData));
        return null;
    }

    @Override
    public EvaluateTxResponse evaluateTx(byte[] cborData) throws InvalidParameterException {
        if (cborData.length == 0) {
            throw new InvalidParameterException();
        }
//        Response response = send(new EvaluateTxRequest(cborData));
//        if (response.getFault() == null)
//            return (EvaluateTxResponse) response;
//        else
//            throw new RuntimeException(response.toString());
        return null;
    }

    /* LocalStateQuery */

    @Override
    public Long blockHeight() {
        return ((OgmiosResponse.BlockHeight) send(new BlockHeightRequest())).getResult();
    }

    @Override
    public Point chainTip() {
        return ((OgmiosResponse.ChainTip) send(new ChainTipRequest())).getResult();
    }

    @Override
    public OgmiosResponse.CurrentProtocolParameters currentProtocolParameters() {
        return (OgmiosResponse.CurrentProtocolParameters) send(new CurrentProtocolParametersRequest());
    }

    @Override
    public DelegationsAndRewards delegationsAndRewards(List<String> credentials) {
//        return (DelegationsAndRewards) send(new DelegationsAndRewardsRequest(credentials));
        return null;
    }

    @Override
    public Bound eraStart() {
        return ((OgmiosResponse.EraStartResponse) send(new EraStartRequest())).getResult();
    }

    @Override
    public List<EraSummary> eraSummaries() {
        return ((OgmiosResponse.EraSummaryResponse) send(new EraSummariesRequest())).getResult();
    }

    @Override
    public Long currentEpoch() {
        return ((OgmiosResponse.CurrentEpoch) send(new CurrentEpochRequest())).getResult();
    }

    @Override
    public GenesisConfig genesisConfig() {
//        return (GenesisConfig) send(new GenesisConfigRequest());
        return null;
    }


    @Override
    public LedgerTip ledgerTip() {
//        return (LedgerTip) send(new LedgerTipRequest());
        return null;

    }

    @Override
    public NonMyopicMemberRewards nonMyopicMemberRewardsByCredentials(List<String> credentials) {
//        return (NonMyopicMemberRewards) send(new NonMyopicMemberRewardsRequest(credentials));
        return null;
    }

    @Override
    public NonMyopicMemberRewards nonMyopicMemberRewardsByAmounts(List<Integer> amounts) {
//        return (NonMyopicMemberRewards) send(new NonMyopicMemberRewardsAmountsRequest(amounts));
        return null;
    }

    @Override
    public PoolIds poolIds() {
//        return (PoolIds) send(new PoolIdsRequest());
        return null;
    }

    @Override
    public PoolParameters poolParameters(List<String> bech32PoolIds) {
//        return (PoolParameters) send(new PoolParametersRequest(bech32PoolIds));
        return null;
    }

    @Override
    public PoolsRanking poolsRanking() {
//        return (PoolsRanking) send(new PoolsRankingRequest());
        return null;
    }

    @Override
    public ProposedProtocolParameters proposedProtocolParameters() {
//        return (ProposedProtocolParameters) send(new ProposedProtocolParametersRequest());
        return null;
    }

    @Override
    public RewardsProvenance rewardsProvenance() {
//        return (RewardsProvenance) send(new RewardsProvenanceRequest());
        return null;
    }

    @Override
    public StakeDistribution stakeDistribution() {
//        return (StakeDistribution) send(new StakeDistributionRequest());
        return null;
    }

    @Override
    public SystemStart systemStart() {
//        return (SystemStart) send(new SystemStartRequest());
        return null;
    }

    @Override
    public List<Utxo> utxoByAddress(String address) {
        OgmiosResponse.UtxoByAddress utxoByAddress = (OgmiosResponse.UtxoByAddress) send(new UtxoByAddressRequest(address));
        log.info("utxoByAddress: {}", utxoByAddress);
        return utxoByAddress.getResult();
    }
}
