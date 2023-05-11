import React from "react";


export default function Login() {
    return (
        <div className="w-full h-screen flex bg-gray-100">
            <div
                className="grid grid-cols-1 md:grid-cols-2 m-auto h-[550px] shadow-lg shadow-gray-500 sm:max-w-[800px]">
                <div className="w-full h-[550px] hidden md:block">
                    <img className="w-full h-full" src="/assets/images/login.png" alt="/"/>
                </div>
                <div className="p-4 flex flex-col justify-around bg-white">
                    <form>
                        <h2 className="text-4xl font-bold text-center mb-8">WarehouseMS</h2>
                        <div>
                            <input className="border p-2 my-2 w-full" type="text" placeholder="Username"/>
                            <input className="border p-2 my-2 w-full" type="password" placeholder="Password"/>
                        </div>
                        <button className="w-full py-2 my-4 bg-gray-300 hover:bg-gray-400">Sign in</button>
                        <p className="text-center">Forgot Username or Password?</p>
                    </form>
                    <p className="text-center">Sign Up</p>
                </div>
            </div>
        </div>
    );
}
