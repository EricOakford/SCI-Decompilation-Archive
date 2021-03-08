;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include sci.sh)
(use Main)
(use Feature)
(use Motion)
(use Game)
(use System)

(public
	kiz 0
	yoFlo 1
	sBeamOut 2
)

(class kiz of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		modnum 301
	)
	
	(method (init)
		(super init: &rest)
		(if (not (Btst 16)) (self setScript: sProdPlayer))
		(if
			(or
				(== (theMusic1 number?) 35)
				(and (== (theMusic1 number?) 124) (== curRoomNum 325))
				(and (== (theMusic1 number?) 17) (!= curRoomNum 305))
			)
			(theMusic1 number: 15 loop: -1 play: 0 fade: 127 10 5 0)
		)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 300 305 310 315 320 325 330 335))
		(= initialized 0)
		(if
			(or
				(== (theMusic1 number?) 35)
				(and (== (theMusic1 number?) 124) (== n 325))
				(and (== (theMusic1 number?) 17) (!= n 305))
			)
			(theMusic1 fade: 0 5 10 1)
		)
		(super newRoom: n)
	)
)

(instance yoFlo of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(32
					(curRoom setScript: sBeamOut)
					(return 1)
				)
				(else  (return 0))
			)
		)
	)
)

(instance sBeamOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 180 self)
			)
			(1
				(ego view: 14 loop: 0 cel: 0 setCycle: End self)
			)
			(2
				(cond 
					((not (Btst 9))
						(cond 
							((not (Btst 16)) (= register 0) (messager say: 1 32 6 0 self 301))
							((!= curRoomNum 300) (= register 0) (messager say: 1 32 2 0 self 301))
							((not (ego has: 20)) (= register 0) (messager say: 1 32 3 0 self 301))
							(else (= register 1) (messager say: 1 32 4 0 self 301))
						)
					)
					((not (Btst 212))
						(if (!= curRoomNum 325)
							(= register 0)
							(messager say: 1 32 2 0 self 301)
						else
							(= register 1)
							(messager say: 1 32 4 0 self 301)
						)
					)
					((!= curRoomNum 300) (= register 0) (messager say: 1 32 2 0 self 301))
					(else (= register 1) (messager say: 1 32 4 0 self 301))
				)
			)
			(3 (ego setCycle: Beg self))
			(4
				(cond 
					((not register)
						(NormalEgo 0)
						(ego loop: 2)
						(theGame handsOn:)
						(self dispose:)
					)
					(
					(and (not (& global169 $0020)) (== curRoomNum 325))
						(if ((ScriptID 325 1) script?)
							(((ScriptID 325 1) script?) dispose:)
						)
						((ScriptID 325 1)
							view: 33
							setLoop: -1
							setLoop: 0
							cel: ((ScriptID 325 1) lastCel:)
							x: 293
							y: 165
							setCycle: CT 2 -1 self
						)
					)
					(else (= cycles 1))
				)
			)
			(5
				(theMusic2 number: 260 setLoop: 1 play:)
				(ego
					view: 6
					loop: 0
					cel: (ego lastCel:)
					cycleSpeed: 6
					setCycle: Beg self
				)
				(if
				(and (not (& global169 $0020)) (== curRoomNum 325))
					((ScriptID 325 1) setCycle: Beg)
				)
				(if (ego has: 17) (ego put: 17))
			)
			(6
				(Bset 9)
				(if (ego has: 17) (ego put: 17))
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance sProdPlayer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(if (and (Btst 66) (not (Btst 67)))
					(Bset 67)
					(messager say: 3 0 0 0 self 301)
				else
					(= cycles 1)
				)
			)
			(2 (= seconds 180))
			(3
				(if (not (Btst 16))
					(messager say: 2 0 0 (Random 1 3) self 301)
				else
					(self dispose:)
				)
			)
			(4 (= state -1) (= cycles 1))
			(5 (self dispose:))
		)
	)
)
