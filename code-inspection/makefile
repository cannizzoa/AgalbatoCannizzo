VPATH = tex-parts

CI.pdf : CI.tex CI-p1.tex CI-p2.tex CI-p3.tex
	pdflatex CI.tex
	pdflatex CI.tex
	rm *.aux
	rm *.log
	rm *.out
	rm *.toc
	
.PHONY : install
install : 
	cp CI.pdf ../Deliveries/'Agalbato, Cannizzo - Code Inspection v1.pdf'
