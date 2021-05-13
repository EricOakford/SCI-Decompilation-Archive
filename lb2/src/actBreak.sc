;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include sci.sh)
(use Main)
(use LBRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	actBreak 0
)

(local
	local0
	local1
	local2
	local3 =  5
	local4 =  12
	local5 =  43
	local6 =  58
	local7 =  61
	local8
)
(instance actBreak of LBRoom
	(properties
		picture 780
		style $000a
	)
	
	(method (init)
		(LoadMany 128 26)
		(LoadMany 132 30)
		(theGame handsOff:)
		(switch currentAct
			(0 (= local0 230))
			(1
				(= local8 (/ (* score 100) local3))
				(= local0 330)
			)
			(2
				(= local8 (/ (* score 100) local4))
				(= local0 355)
			)
			(3
				(= local8 (/ (* score 100) local5))
				(= global111 11)
				(= local0 (if (== prevRoomNum 620) 610 else 510))
			)
			(4
				(= local8 (/ (* score 100) local6))
				(= local0 420)
			)
			(5
				(= local8 (/ (* score 100) local7))
				(= local0 750)
			)
		)
		(super init: &rest)
		(cond 
			((and (< -1 local8) (< local8 21)) (= local1 2) (= local2 global131) (++ global131))
			((and (< 20 local8) (< local8 41)) (= local1 3) (= local2 global132) (++ global132))
			((and (< 40 local8) (< local8 61)) (= local1 4) (= local2 global133) (++ global133))
			((and (< 60 local8) (< local8 81)) (= local1 5) (= local2 global134) (++ global134))
			((and (< 80 local8) (< local8 101)) (= local1 6) (= local2 global135) (++ global135))
		)
		(actView init: cel: currentAct)
		(theMusic number: 30 flags: 1 loop: -1 play:)
		(self setScript: sBreakIt)
	)
)

(instance sBreakIt of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(curRoom picture: 26 drawPic: 26)
				(= cycles 2)
			)
			(1
				(= temp0 (CelHigh 26 0 currentAct))
				(actView setMotion: MoveTo 150 (+ 101 (/ temp0 2)) self)
			)
			(2
				(= temp0 (CelHigh 26 1 currentAct))
				(titleView y: (+ 134 temp0) init: cel: currentAct)
				(= seconds 3)
			)
			(3
				(actView setMotion: MoveTo 150 65 self)
			)
			(4
				(= temp0 (CelHigh 26 1 currentAct))
				(titleView
					setMotion: MoveTo 150 (+ 101 (/ temp0 2)) self
				)
			)
			(5
				(theMusic fade: 0 40 10 1)
				(= seconds 3)
			)
			(6
				(actView dispose:)
				(titleView setMotion: MoveTo 150 65 self)
			)
			(7
				(titleView dispose:)
				(curRoom drawPic: 780 10)
				(= seconds 2)
			)
			(8
				(narrator talkWidth: 120)
				(if (and local1 local2 currentAct)
					(messager say: local1 0 0 local2 self)
				else
					(= cycles 1)
				)
			)
			(9
				(theMusic fade: 0 10 20 1)
				(++ currentAct)
				(= triggeredEvents 0)
				(curRoom newRoom: local0)
				(self dispose:)
			)
		)
	)
)

(instance actView of Actor
	(properties
		x 150
		y 141
		view 26
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 2)
			(sBreakIt changeState: 8)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance titleView of Actor
	(properties
		x 150
		y 161
		view 26
		loop 1
	)
)
