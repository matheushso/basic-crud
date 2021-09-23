import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useHistory } from 'react-router-dom';
import { Navbar, Button, Form, FormControl, Table } from 'react-bootstrap';


const Developer = () => {
    const [developers, setDevelopers] = useState({content: [], pageable: {pageNumber: 0}, totalPages: 0});
    const [developer, setDeveloper] = useState({nome: "", sexo: "", idade: "", hobby: "", dataNascimento: ""});
    const [termoDeBusca, setTermoDeBusca] = useState("");
    const [páginaRequerida, setPáginaRequerida] = useState(0);

    const doGetDevelopers = async (páginaRequerida) => {
        const response = await axios.get(`/developers?termo=${termoDeBusca}&page=${páginaRequerida}`);
        setPáginaRequerida(páginaRequerida);
        setDevelopers(response.data);
    }

    // useEffect(() => {
    //     doGetDevelopers(0);
    // }, [])

    const doExcluirDevelopers = async (id) => {
        await axios.delete(`/developers/${id}`);
        doGetDevelopers(páginaRequerida);
    }

    const handleExcluir = (id) => {
        if (window.confirm("Deseja excluir?")) {
            doExcluirDevelopers(id);
        }
    }

    const handleSearchInputChange = (event) => {
        setTermoDeBusca(event.target.value);
    }

    const handleSearch = (event) => {
        doGetDevelopers(0);
    }

    const tableData = developers.content.map(row => {
        return <tr key={row.id}>
            <td>{row.nome}</td>
            <td>{row.sexo}</td>
            <td>{row.idade}</td>
            <td>{row.hobby}</td>
            <td>{row.dataNascimento}</td>
            <td>
                <Button variant="warning" onClick={() => doPut(row.id)}>Editar</Button> {' '}
                <Button variant="danger" onClick={() => handleExcluir(row.id)}>Excluir</Button> {' '}
            </td>
        </tr>;
    })
    
    useEffect(() => {
        console.log("executando doGetProdutos " + páginaRequerida);
        doGetDevelopers(páginaRequerida);        
    }, [páginaRequerida]);

    const requestPage = (requestedPage) => {
        console.log(`requestedPage=${requestedPage}   totalPages=${developers.totalPages}   páginaRequerida=${páginaRequerida}`);
        if (requestedPage <= 0) {
            requestedPage = 0;
        }
        if (requestedPage >= developers.totalPages) {
            requestedPage = developers.totalPages-1;
        }
        console.log(requestedPage);
        setPáginaRequerida(requestedPage);
    }

    //ADD NEW

    const doPut = async (id) => {
        await axios.put(`/developers/${id}`, developer);
        window.location.reload();
    }

    const doPost = async () => {
        await axios.post("/developers", developer);
        window.location.reload();
    }

    const handleChange = (event) => {
        const novoDeveloper = { ...developer, [event.target.name]: event.target.value };
        setDeveloper(novoDeveloper);
    }

    return (
        <div>
            <div>
                <Navbar bg="light" expand="lg">
                    <Navbar.Brand><h2>Developer Basic CRUD</h2></Navbar.Brand>
                    <Navbar.Toggle aria-controls="navbarScroll" />
                    <Navbar.Collapse id="navbarScroll">
                        <Form className="d-flex">
                        <FormControl
                            type="search"
                            placeholder="Pesquisar"
                            className="mr-2"
                            aria-label="Pesquisar"
                            onChange={handleSearchInputChange}
                        />
                        <Button variant="outline-success" onClick={handleSearch}>Pesquisar</Button>
                        <button onClick={() => requestPage(developers.pageable.pageNumber-1)}>{'<'}</button>
                        Página {developers.pageable.pageNumber+1} de {developers.totalPages}
                        <button onClick={() => requestPage(developers.pageable.pageNumber+1)}>{'>'}</button>
                        </Form>
                    </Navbar.Collapse>
                </Navbar>
            </div>
            <div>

            <form>
                <Table striped bordered hover variant="dark">
                    <thead>
                        <tr>
                        <th>Nome</th>
                        <th>Sexo</th>
                        <th>Idade</th>
                        <th>Hobby</th>
                        <th>Data nascimento</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" name="nome" onChange={handleChange} value={developer.nome}></input></td>
                            <td><input type="text" name="sexo" onChange={handleChange} value={developer.sexo}></input></td>
                            <td><input type="text" name="idade" onChange={handleChange} value={developer.idade}></input></td>
                            <td><input type="text" name="hobby" onChange={handleChange} value={developer.hobby}></input></td>
                            <td><input type="text" name="dataNascimento" onChange={handleChange} value={developer.dataNascimento}></input></td>
                        </tr>
                    </tbody>
                    <Button variant="success" onClick={doPost}>Salvar</Button>{' '}
                </Table>
            </form>
            

            </div>
            
            <Table striped bordered hover variant="dark">
                <thead>
                    <tr>
                    <th>Nome</th>
                    <th>Sexo</th>
                    <th>Idade</th>
                    <th>Hobby</th>
                    <th>Data nascimento</th>
                    <th>Alterar</th>
                    </tr>
                </thead>
                <tbody>
                    {tableData}
                </tbody>
            </Table>
        </div>
    )
}

export default Developer;