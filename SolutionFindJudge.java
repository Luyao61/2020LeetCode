class SolutionFindJudge implements LeetcodeSolution{
    public int findJudge(int N, int[][] trust) {
        // for each i, count the number of prople trusts i,
        int[] trustCount = new int[N];
        // for each i, if i trusts any one, he is not the judge,
        boolean[] isNotJudge = new boolean[N];

        for(int[] t: trust) {
            isNotJudge[t[0] - 1] = true;
            trustCount[t[1] - 1]++;
        }
        for (int i = 0; i < isNotJudge.length; i++) {
            if (isNotJudge[i] == false && trustCount[i] == N - 1) {
                return i + 1;
            }
        }

        return -1;
    }
    
    public void test() {
        int N  = 2;
        int[][] trust = new int[][] {{1,2}};
        int judge = findJudge(N, trust);
        System.out.println(judge);
    }
}