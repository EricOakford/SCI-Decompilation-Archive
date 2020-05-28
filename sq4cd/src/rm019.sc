;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Sound)
(use Motion)
(use System)

(public
	rm019 0
)

(local
	[local0 7]
	shotY =  47
	shotX =  255
)
(procedure (localproc_027b param1 param2 param3 param4 &tmp temp0 temp1 temp2)
	(if (< param1 param3)
		(= temp0 (- param1 1))
		(= temp1 (+ param3 1))
	else
		(= temp0 (- param3 1))
		(= temp1 (- param1 1))
	)
	(= temp2
		(Graph
			GSaveBits
			temp0
			(- param2 1)
			temp1
			(+ param4 1)
			1
		)
	)
	(Graph
		GDrawLine
		param1
		param2
		param3
		param4
		(VGAOrEGA myTextColor3 myTextColor17)
		-1
		-1
	)
	(Graph
		GReAnimate
		temp0
		(- param2 1)
		temp1
		(+ param4 1)
	)
	(localproc_031c)
	(Wait 1)
	(Wait 2)
	(Graph GReAnimate temp2)
	(Graph
		GReAnimate
		temp0
		(- param2 1)
		temp1
		(+ param4 1)
	)
)

(procedure (localproc_031c)
	(Animate (cast elements?) TRUE)
	(egoHead setCel: 1)
	(trHead setLoop: 2 setCel: 1)
	(egoMouth hide:)
	(trMouth hide:)
	(Animate (cast elements?) TRUE)
	(egoHead setCel: 0)
	(trHead setCel: 0)
	(egoMouth show:)
	(trMouth show:)
)

(instance rm019 of SQRoom
	(properties
		picture 19
	)
	
	(method (init &tmp [temp0 50])
		(Load VIEW 1018)
		(Load VIEW 28)
		(Load SOUND 105)
		(super init:)
		(shot1 init:)
		(shot2 init:)
		(egoHead init:)
		(egoMouth init:)
		(trHead init:)
		(self setScript: startScript setRegions: INTRO)
	)
)

(instance startScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(tROGER
					init: 0 0 egoMouth
					say: 1 0 2 64 5 132 67 300 25 myTextColor6 26 myLowlightColor 27 0
				)
				(= seconds 1)
			)
			(2 (trHead setCycle: EndLoop self))
			(3
				(trHead setLoop: 2 setCel: 0)
				(trMouth init:)
				(= cycles 10)
			)
			(4
				(if (!= (tROGER ticks?) -1) (-- state))
				(= cycles 1)
			)
			(5
				(if (== msgType 1) (egoMouth setCycle: 0))
				(= cycles 2)
			)
			(6
				(tTIMERIPPER
					init: 0 0 trMouth
					say: 1 0 2 64 138 132 67 180 25 myTextColor6 26 myLowlightColor 27 1
				)
				(= seconds 2)
			)
			(7
				(egoMouth setCel: 0)
				(= cycles 1)
			)
			(8
				(if (!= (tTIMERIPPER ticks?) -1) (-- state))
				(= cycles 1)
			)
			(9
				(localproc_027b shotY shotX 51 319)
				(shotSound play:)
				(shot1 posn: shotX shotY setCycle: EndLoop self)
				(egoMouth cel: 2)
				(= cycles 5)
			)
			(10
				(shot1 dispose:)
				(= cycles 2)
			)
			(11
				(= shotX (- shotX 4))
				(= shotY (- shotY 3))
				(localproc_027b shotY shotX 51 319)
				(shotSound play:)
				(shot2 posn: shotX shotY setCycle: EndLoop self)
				(= cycles 5)
			)
			(12
				(shot2 dispose:)
				(Animate (cast elements?) 0)
				(curRoom newRoom: 20)
			)
		)
	)
)

(instance shotSound of Sound
	(properties
		number 105
	)
)

(instance shot1 of Sq4Prop
	(properties
		view 28
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance shot2 of Sq4Prop
	(properties
		view 28
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance egoHead of Sq4Prop
	(properties
		x 41
		y 97
		view 1018
	)
)

(instance trHead of Sq4Prop
	(properties
		x 187
		y 109
		view 1018
		loop 4
		priority 14
		signal fixPriOn
	)
)

(instance egoMouth of Sq4Prop
	(properties
		x 53
		y 77
		view 1018
		loop 1
	)
)

(instance trMouth of Sq4Prop
	(properties
		x 181
		y 99
		view 1018
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance tROGER of FaceTalker
	(properties
		noun ROGER
		talkerNum ROGER
		finalMouth -1
	)
)

(instance tTIMERIPPER of FaceTalker
	(properties
		noun TIMERIPPER
		talkerNum TIMERIPPER
	)
)
