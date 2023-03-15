import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "../App.css";
import React, { useState } from "react";
import {GroupManagement} from "./GroupManagement";
import {InventoryManagement} from "./InventoryManagement";
import RecipeManagement from "./RecipeManagement";
import ExpenseManagement from "./ExpenseManagement";

export const HomePage = () => {
const [activeTab, setActiveTab] = useState("");
const renderTab = () => {
    switch (activeTab) {
      case "GroupManagement":
        return <GroupManagement />;
      case "InventoryManagement":
        return <InventoryManagement />;
      case "RecipeManagement":
        return <RecipeManagement />;
      case "ExpenseManagement":
        return <ExpenseManagement />;
    }
  };
return (
    <div className="Home">
      <header>
        <nav>
          <ul>
            <li>
              <button onClick={() => setActiveTab("GroupManagement")}>
                Group Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("InventoryManagement")}>
                Inventory Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("RecipeManagement")}>
                Recipe Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("ExpenseManagement")}>
                Expense Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("Home")}>Home</button>
            </li>
          </ul>
        </nav>
      </header>
      <main>{renderTab()}</main>
    </div>
  );
}
export default HomePage;
