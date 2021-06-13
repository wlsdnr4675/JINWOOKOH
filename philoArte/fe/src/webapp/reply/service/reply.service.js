import axios from 'axios';
const register = (fd) => {
    console.log(fd);

    return axios
        .post(`/replies/register`, fd, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        })
        .then((response) => {
            return response.data;
        });
};

const list = (reviewId) => {
    return axios.get(`/replies/list/${reviewId}`);
};

const modify = (reply) => {
    console.log('modify');
    return axios
        .put(`/replies/modify/` + reply.rno, reply, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        })
        .then((response) => {
            return response.data;
        });
};

const deletes = (rno) => {
    console.log('Delete reply');
    return axios.delete(`/replies/remove/${rno}`, { data: { ...rno } });
};

export default { register, list, modify, deletes };
