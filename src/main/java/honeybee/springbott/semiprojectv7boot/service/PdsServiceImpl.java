package honeybee.springbott.semiprojectv7boot.service;

import honeybee.springbott.semiprojectv7boot.dao.PdsDAO;
import honeybee.springbott.semiprojectv7boot.model.Board;
import honeybee.springbott.semiprojectv7boot.model.Pds;
import honeybee.springbott.semiprojectv7boot.model.PdsAttach;
import honeybee.springbott.semiprojectv7boot.utils.PdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service("pdssrv")
public class PdsServiceImpl implements PdsService{
    @Autowired
    private PdsDAO pdsdao;
    @Autowired
    private PdsUtils pdsUtils;
    @Override
    public Map<String, Object> showPds(int cpage) {
        return null;
    }

    @Override
    public Map<String, Object> showPds(int cpage, String ftype, String fkey) {
        return null;
    }

    @Override
    public Map<String, Object> newPds(Pds pds) {
        pds.setUuid(pdsUtils.makeUUID()); // 식별코드 생성
        int pno = pdsdao.insertPds(pds); // 폼 데이터 디비에 저장

        // 첨부파일을 시스템에 저장할때 사용할 정보를 Map에 저장
        Map<String, Object> pinfo = new HashMap<>();
        pinfo.put("pno",pno);
        pinfo.put("uuid",pds.getUuid());
        return pinfo;
    }

    @Override
    public boolean newPdsAttach(MultipartFile attach, Map<String, Object> pinfo) {
        // 첨부파일 업로드 처리
        PdsAttach pa = pdsUtils.processUpload(attach,pinfo);
        // 첨부파일 정보 디비에 저장
        int pano = pdsdao.insertPdsAttach(pa);

        return (pano > 0) ? true : false;
    }

    @Override
    public Board readOneBoard(int bno) {
        return null;
    }
}