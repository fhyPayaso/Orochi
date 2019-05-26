import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Card, Button, Icon, List } from 'antd';

import Ellipsis from '@/components/Ellipsis';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './Application.less';
import ApplicationLayout from '../../layouts/ApplicationLayout';

@connect(({ list, loading }) => ({
    list,
    loading: loading.models.list,
}))
class ApplicationList extends PureComponent {
    componentDidMount() {
        const { dispatch } = this.props;
        // 拉取app列表
        dispatch({
            type: 'app/fetch',
            payload: {
                count: 8,
            },
        });
    }

    render() {
        
        const {
            list: { list },
            loading,
        } = this.props;
        const content = (
            <div className={styles.pageHeaderContent}>
                <p>
                    今天也不能偷懒喵。
                </p>
                <div className={styles.contentLink}>
                    <a>
                        <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/MjEImQtenlyueSmVEfUD.svg" />{' '}
                        快速开始
                    </a>
                    <a>
                        <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/NbuDUAuBlIApFuDvWiND.svg" />{' '}
                        平台简介
                    </a>
                    <a>
                        <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/ohOEPSYdDTNnyMbGuyLb.svg" />{' '}
                        接入文档
                    </a>
                </div>
            </div>
        );

        const extraContent = (
            <div className={styles.extraImg}>
                <img
                    alt="这是一个标题"
                    src="https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png"
                />
            </div>
        );

        return (
            <ApplicationLayout>
                <PageHeaderWrapper
                    title="卡片列表"
                    content={content}
                    extraContent={extraContent}
                    style={{ marginTop: 16 + 'px' }}
                >
                    <div className={styles.cardList}>
                        <List
                            rowKey="id"
                            loading={loading}
                            grid={{ gutter: 24, lg: 3, md: 2, sm: 1, xs: 1 }}
                            dataSource={['', ...list]}
                            renderItem={item => {

                                // console.log("item=>>>", item)
                                console.log("props=>>", this.props)
                            
                                return item ? (
                                    <List.Item key={item.id}>
                                        <Card hoverable className={styles.card} actions={[<a>编辑</a>, <a>删除</a>]}>
                                            <Card.Meta
                                                avatar={<img alt="" className={styles.cardAvatar} src={item.avatar} />}
                                                title={<a>{item.name}</a>}
                                                description={
                                                    <Ellipsis className={styles.item} lines={3}>
                                                        {item.description}
                                                    </Ellipsis>
                                                }
                                            />
                                        </Card>
                                    </List.Item>
                                ) : (
                                        <List.Item>
                                            <Button type="dashed" className={styles.newButton}>
                                                <Icon type="plus" /> 新建产品
                                            </Button>
                                        </List.Item>
                                    )
                            }
                            }
                        />
                    </div>
                </PageHeaderWrapper>
            </ApplicationLayout>
        );
    }
}

export default ApplicationList;
