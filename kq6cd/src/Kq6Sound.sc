;;; Sierra Script 1.0 - (do not remove this comment)
(script# 102)
(include sci.sh)
(use Sound)

(public
	Kq6Sound 0
)

(class Kq6Sound of Sound
	(properties
		nodePtr 0
		handle 0
		flags $0001
		number 0
		vol 127
		priority 0
		loop 1
		signal $0000
		prevSignal 0
		dataInc 0
		min 0
		sec 0
		frame 0
		client 0
		owner 0
	)
	
	(method (setPri)
	)
)
