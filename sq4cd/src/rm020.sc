;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use LoadMany)
(use Motion)
(use System)

(public
	rm020 0
)

(local
	local0
)
(instance rm020 of SQRoom
	(properties
		picture 20
		style FADEOUT
	)
	
	(method (init &tmp [temp0 50])
		(LoadMany VIEW 20 21)
		(super init:)
		(self setRegions: INTRO)
		(switch prevRoomNum
			(15
				(tr
					view: 21
					setLoop: 0
					cel: 0
					posn: 237 94
					setCycle: Walk
					init:
				)
				(roger
					view: 21
					setLoop: 2
					cel: 0
					posn: 254 91
					setCycle: Walk
					init:
				)
				(self setScript: firstTimeScript)
			)
			(else 
				(roger view: 20 setLoop: 5 setCel: 0 posn: 174 111 init:)
				(tr view: 20 setLoop: 0 cel: 0 posn: 195 116 init:)
				(self setScript: ripScript)
			)
		)
	)
)

(instance ripScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 10)
			(roger x: (- (roger x?) register))
			(++ register)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(trHead init:)
				(tTIMERIPPER
					init: 0 0 trHead
					say: 1 self 2 64 60 15 67 250 25 myTextColor6 26 myLowlightColor 27 1
				)
			)
			(2
				(trHead dispose:)
				(globalSound number: 121 loop: 1 vol: 127 play:)
				(roger setCycle: EndLoop)
				(tr setPri: (+ (roger priority?) 1) setCycle: EndLoop self)
			)
			(3
				(tr setLoop: 1 cel: 0 setCycle: EndLoop self)
				(globalSound number: 821 vol: 127 play:)
				(rip init: cycleSpeed: 6 setCycle: Forward)
			)
			(4
				(tr stopUpd:)
				(roger stopUpd:)
				(= seconds 3)
			)
			(5
				(rip setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(6
				(rip setLoop: 4 setCel: 0 setCycle: Forward)
				(= cycles 1)
			)
			(7
				(trHead posn: 195 76 setLoop: 8 init:)
				(tTIMERIPPER
					say: 2 self 2 64 30 15 67 280 25 myTextColor6 26 myLowlightColor 27 1
				)
			)
			(8
				(trHead dispose:)
				(= seconds 1)
			)
			(9
				(roger setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(10
				(rip setPri: 11)
				(roger
					setPri: 12
					setStep: 7 1
					moveSpeed: 0
					setMotion: MoveTo 105 115 self
				)
			)
			(11
				(rip setLoop: 3 cel: 16 setCycle: BegLoop self)
			)
			(12
				(globalSound fade:)
				(rip dispose:)
				(= cycles 3)
			)
			(13
				(curRoom newRoom: (if (== prevRoomNum 19) 21 else 19))
			)
		)
	)
)

(instance firstTimeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tr moveSpeed: 2 setMotion: MoveTo 196 110 self)
				(roger moveSpeed: 2 setMotion: MoveTo 174 113 self)
			)
			(1
				(tr view: 5 setLoop: 4 posn: 196 108)
			)
			(2
				(roger view: 20 setLoop: 5 setCel: 0 posn: 174 111)
				(= cycles 2)
			)
			(3 (curRoom newRoom: 19))
		)
	)
)

(instance tr of Sq4Actor
	(properties
		view 21
		priority 11
		signal (| ignrAct fixPriOn)
		xStep 4
	)
)

(instance roger of Sq4Actor
	(properties
		priority 12
		signal fixPriOn
		illegalBits $0000
		xStep 4
	)
)

(instance rip of Sq4Prop
	(properties
		x 122
		y 92
		view 20
		loop 2
		priority 14
		signal fixPriOn
	)
)

(instance trHead of Sq4Prop
	(properties
		x 194
		y 78
		view 20
		loop 7
		priority 15
		signal fixPriOn
	)
)

(instance tTIMERIPPER of FaceTalker
	(properties
		noun TIMERIPPER
		talkerNum TIMERIPPER
	)
)
