```C++
#include <bits/stdc++.h>
using namespace std;

const int INF = INT_MAX;

// Hàm BFS nhiều nguồn
vector<int> multiSourceBfs(int vCount, const vector<vector<int>>& adj, const vector<int>& sourceNodes)
{
    // Khởi tạo mảng khoảng cách với giá trị vô cùng
    vector<int> distance(vCount + 1, INF);
    
    // Hàng đợi để xử lý các đỉnh
    queue<int> q;

    // --- THAY ĐỔI DUY NHẤT NẰM Ở ĐÂY ---
    // Thiết lập cho TẤT CẢ các đỉnh nguồn
    for (int startNode : sourceNodes)
    {
        if (distance[startNode] == INF) // Đề phòng nguồn trùng lặp
        {
            distance[startNode] = 0;
            q.push(startNode);
        }
    }
    // ------------------------------------

    // Vòng lặp chính của BFS vẫn giữ nguyên
    while (!q.empty())
    {
        int u = q.front();
        q.pop();

        for (int v : adj[u])
        {
            if (distance[v] == INF)
            {
                distance[v] = distance[u] + 1;
                q.push(v);
            }
        }
    }
    return distance;
}

```