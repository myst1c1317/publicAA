package com.aa.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aa.bean.Tourthing;
import com.aa.mapper.TourthingMapper;
import com.aa.service.TourthingService;
@Service
public class TourthingServiceImpl extends ServiceImpl<TourthingMapper, Tourthing> implements TourthingService{

}
