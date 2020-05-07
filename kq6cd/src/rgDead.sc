;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include sci.sh)
(use Kq6Procs)
(use RandCyc)
(use Game)
(use System)

(public
	rgDead 0
	proc70_1 1
)

(procedure (proc70_1 param1 param2 &tmp temp0 temp1)
	(= temp0 -1)
	(while (= temp1 (WordAt param2 (++ temp0)))
		((Clone param1)
			posn: temp1 (WordAt param2 (++ temp0))
			loop: (WordAt param2 (++ temp0))
			cel: (Random 0 3)
			setCycle: RandCycle
			init:
		)
	)
)

(class rgDead of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		flag1 0
		stateOf690 0
		songWaiting 0
		loopWaiting 0
	)
	
	(method (cue)
		(super cue:)
		(Bset 121)
	)
	
	(method (newRoom n)
		(= keep
			(OneOf n 600 605 615 620 630 640 650 660 670 680 690)
		)
		(= initialized 0)
		(super newRoom: n &rest)
	)
	
	(method (isSet param1)
		(return (& flag1 param1))
	)
	
	(method (clrIt param1)
		(= flag1 (& flag1 (^ flag1 param1)))
	)
	
	(method (setIt param1 &tmp temp0)
		(= temp0 param1)
		(= flag1 (| flag1 param1))
		(return (& temp0 param1))
	)
)
