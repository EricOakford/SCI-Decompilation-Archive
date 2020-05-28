;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use MoveCyc)
(use Sound)
(use Motion)
(use System)

(public
	rm340 0
)

(local
	[leftZapCycle 29] = [5 0 86 157 5 0 82 156 5 0 78 155 5 0 79 151 5 0 80 148 5 0 81 144 5 0 84 144 -32768]
	[rightZapCycle 29] = [6 0 160 168 6 0 166 167 6 0 173 166 6 0 174 162 6 0 175 158 6 0 176 153 6 0 172 153 -32768]
)
(instance rm340 of SQRoom
	(properties
		picture 340
	)
	
	(method (init)
		(super init:)
		(zondraArm init:)
		(zondraEyes init:)
		(zondraMouth init:)
		(thoreenMouth init:)
		(rogerMouth init:)
		(rogerTalker init: 0 0 rogerMouth)
		(rogerLegs init:)
		(rightZap init:)
		(leftZap init:)
		(pantsSfx init:)
		(self setScript: tortureScript)
	)
)

(instance tortureScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rogerLegs stopUpd:)
				(rightZap stopUpd:)
				(leftZap stopUpd:)
				(= seconds 3)
			)
			(1
				(music hold: 3)
				(zondraTalker
					init: 0 zondraEyes zondraMouth
					say: 8 self 2 64 2 170 25 myTextColor6 26 myLowlightColor 27 0 67 315
				)
			)
			(2
				(zondraArm cycleSpeed: 12 setCycle: EndLoop)
				(= cycles 3)
			)
			(3
				(zondraArm setCycle: BegLoop)
				(rightZap setCycle: MoveCycle @rightZapCycle)
				(leftZap setCycle: MoveCycle @leftZapCycle self)
			)
			(4
				(= start state)
				(rightZap setCycle: BegLoop)
				(aSound init: play:)
				(leftZap setCycle: BegLoop self)
			)
			(5
				(if (< (++ register) 5)
					(self init:)
				else
					(aSound stop:)
					(pantsSfx play:)
					(rogerLegs setCycle: EndLoop self)
				)
			)
			(6
				(rogerLegs stopUpd:)
				(rightZap stopUpd:)
				(leftZap stopUpd:)
				(= cycles 2)
			)
			(7
				(rogerTalker
					say: 3 self 2 64 2 170 25 myTextColor7 26 myLowlightColor 27 1 67 315
				)
			)
			(8
				(rogerTalker
					say: 4 self 2 64 2 170 25 myTextColor7 26 myLowlightColor 27 1 67 315
				)
			)
			(9
				(zondraTalker
					say: 9 self 2 64 2 170 25 myTextColor6 26 myLowlightColor 27 0 67 315
				)
			)
			(10
				(zondraTalker
					say: 10 self 2 64 2 170 25 myTextColor6 26 myLowlightColor 27 0 67 315
				)
			)
			(11
				(zondraTalker
					say: 11 self 2 64 2 170 25 myTextColor6 26 myLowlightColor 27 0 67 315
				)
			)
			(12
				(zondraTalker
					say: 12 self 2 64 2 170 25 myTextColor6 26 myLowlightColor 27 0 67 315
				)
			)
			(13
				(thoreenTalker
					modNum: 322
					init: 0 0 thoreenMouth
					say: 2 self 2 64 170 170
				)
			)
			(14
				(zondraTalker dispose:)
				(thoreenTalker dispose:)
				(rogerTalker dispose:)
				(curRoom newRoom: 345)
			)
		)
	)
)

(instance aSound of Sound
	(properties
		number 141
		loop -1
	)
)

(instance pantsSfx of Sound
	(properties
		number 151
	)
)

(instance zondraArm of Sq4Prop
	(properties
		x 35
		y 38
		view 1340
	)
)

(instance rogerLegs of Sq4Prop
	(properties
		x 136
		y 94
		view 1340
		loop 7
		priority 10
		signal fixPriOn
	)
)

(instance rightZap of Sq4Prop
	(properties
		x 150
		y 168
		view 1340
		loop 6
		priority 7
		signal fixPriOn
	)
)

(instance leftZap of Sq4Prop
	(properties
		x 130
		y 166
		view 1340
		loop 5
		priority 7
		signal fixPriOn
	)
)

(instance zondraTalker of FaceTalker
	(properties
		noun ZONDRA
		modNum 322
		talkerNum ZONDRA
	)
)

(instance zondraMouth of Sq4Prop
	(properties
		x 25
		y 7
		view 1341
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance zondraEyes of Sq4Prop
	(properties
		x 44
		y 17
		view 1341
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance thoreenTalker of FaceTalker
	(properties
		noun THOREEN
		modNum 322
		talkerNum THOREEN
		tpType 2
	)
)

(instance thoreenMouth of Sq4Prop
	(properties
		x 260
		y 11
		view 1342
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance rogerTalker of FaceTalker
	(properties
		noun ROGER
		modNum 322
		talkerNum ROGER
		tpType 2
	)
)

(instance rogerMouth of Sq4Prop
	(properties
		x 149
		y 12
		view 1343
		priority 14
		signal (| ignrAct fixPriOn)
	)
)
