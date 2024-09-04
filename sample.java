// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node==null)
            return null;
        Queue<Node> q=new LinkedList<>();
        Node newNode=new Node(node.val);
        HashMap<Node,Node> hmap=new HashMap<>();
        hmap.put(node,newNode);
        q.add(node);
        while(!q.isEmpty())
        {
            Node curr=q.poll();
            for(Node ne:curr.neighbors){
                if(!hmap.containsKey(ne))
                {
                    Node newNo=new Node(ne.val);
                    hmap.put(ne,newNo);
                    q.add(ne);
                }
                hmap.get(curr).neighbors.add(hmap.get(ne));
            }
        }
        return newNode;
    }
}

// T.C=O(V+E)
// S.C=O(n)



class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res=new ArrayList<>();
        HashSet<String> hashSet=new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        while(!queue.isEmpty())
        {
            int size=queue.size();
            boolean flag=false;
            for(int i=0;i<size;i++)
            {
                String curr=queue.poll();
                if(isBalanced(curr))
                {
                    flag=true;
                    res.add(curr);
                }
                if(!flag){
                    for(int k=0;k<curr.length();k++)
                    {
                        char ch=curr.charAt(k);
                        if(Character.isAlphabetic(ch)) continue;
                        String childs=curr.substring(0,k)+curr.substring(k+1);
                        if(!hashSet.contains(childs))
                        {
                            queue.add(childs);
                            hashSet.add(childs);
                        }
                    }
                }
            }
            if(flag)
                break;
        }
        return res;
    }
    private boolean isBalanced(String s){
        int count=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(Character.isAlphabetic(ch)) continue;
            if(ch=='(')
                count ++;
            else{
                if(count==0)
                    return false;
                count--;
            }
        }
        return count==0;
    }