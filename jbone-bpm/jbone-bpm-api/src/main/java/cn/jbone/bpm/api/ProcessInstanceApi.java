package cn.jbone.bpm.api;

import cn.jbone.bpm.api.dto.request.ReactiveProcessRequestDTO;
import cn.jbone.bpm.api.dto.request.StartProcessRequestDTO;
import cn.jbone.bpm.api.dto.request.StopProcessRequestDTO;
import cn.jbone.bpm.api.dto.request.SuspendProcessRequestDTO;
import cn.jbone.bpm.api.dto.response.StartProcessResponseDTO;
import cn.jbone.common.rpc.Result;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/processInstance")
public interface ProcessInstanceApi {

    /**
     *
     * @param startProcessRequestDTO
     * @return
     */
    @RequestMapping("start")
    Result<StartProcessResponseDTO> startProcess(StartProcessRequestDTO startProcessRequestDTO);

    /**
     *
     * @param stopProcessRequestDTO
     * @return
     */
    @RequestMapping("stop")
    Result<Void> stopProcess(StopProcessRequestDTO stopProcessRequestDTO);

    /**
     *
     * @param suspendProcessRequestDTO
     * @return
     */
    @RequestMapping("suspend")
    Result<Void> suspendProcess(SuspendProcessRequestDTO suspendProcessRequestDTO);

    /**
     *
     * @param reactiveProcessRequestDTO
     * @return
     */
    @RequestMapping("reactive")
    Result<Void> reactiveProcess(ReactiveProcessRequestDTO reactiveProcessRequestDTO);
}
