package com.aa.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aa.bean.Tournumber;
import com.aa.mapper.TournumberMapper;
import com.aa.service.TournumberService;
@Service
public class TournumberServiceImpl extends ServiceImpl<TournumberMapper, Tournumber> implements TournumberService{

}
