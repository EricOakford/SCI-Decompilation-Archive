;;; Sierra Script 1.0 - (do not remove this comment)
(script# 135)
(include sci.sh)
(use Main)
(use Kq6Sound)
(use KQ6Room)
(use Kq6Procs)
(use Scaler)
(use MoveCyc)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm135 0
)

(local
	local0
	local1
	[local2 145] = [0 0 0 7 0 1 3 11 0 2 6 15 0 3 10 19 0 4 14 23 0 5 18 27 0 6 22 31 0 0 26 34 0 1 30 37 0 2 34 40 0 3 39 43 0 4 43 46 0 5 48 49 0 6 53 52 0 0 58 55 0 1 63 57 0 2 69 59 0 3 75 61 0 4 82 63 0 5 89 64 0 6 95 64 0 0 101 63 0 1 107 61 0 2 112 58 0 3 117 55 0 4 122 52 0 5 127 48 0 6 131 44 0 0 135 40 0 1 138 36 0 2 141 31 0 3 143 26 0 4 144 20 0 5 145 14 0 6 145 8 0 0 145 3 -32768]
	[local147 81] = [13 0 151 49 13 0 149 48 12 1 147 47 12 2 145 46 12 3 143 45 12 4 141 44 12 5 139 43 12 6 137 42 9 1 134 40 9 2 131 38 9 3 128 36 6 4 125 33 6 5 122 30 6 6 119 27 6 0 117 24 3 4 114 20 3 5 111 15 0 0 108 13 0 1 105 8 0 2 103 2 -32768]
	[local228 161] = [0 0 316 3 0 1 313 6 0 2 309 10 0 3 304 13 0 4 298 15 0 5 291 16 0 6 284 16 0 0 277 16 0 1 270 16 0 2 262 15 0 3 254 15 0 4 246 15 0 5 239 15 0 6 232 15 0 0 225 15 0 1 217 16 0 2 210 17 0 3 203 18 0 4 196 19 0 5 189 21 0 6 182 23 0 0 175 25 0 1 169 27 0 2 164 30 0 3 164 30 0 4 164 30 0 5 164 30 0 6 164 30 0 0 164 30 0 1 164 30 1 0 165 27 1 0 159 26 1 0 152 24 1 0 146 22 1 0 140 20 1 0 134 17 1 0 128 13 1 0 123 9 1 0 118 5 1 0 114 0 -32768]
	[local389 133] = [0 0 40 3 0 1 40 8 0 2 40 12 0 3 40 15 0 4 40 18 0 5 40 21 0 6 40 24 3 0 40 23 3 1 40 25 3 2 40 27 3 3 40 29 3 4 40 31 3 5 40 33 6 6 40 35 6 0 40 37 6 1 40 39 6 2 40 41 6 3 40 43 6 4 40 45 9 5 40 47 9 6 40 48 9 0 40 49 9 1 40 50 9 2 40 51 9 3 40 52 12 0 40 53 12 1 40 54 12 2 40 55 12 3 40 56 12 4 40 57 12 5 40 58 12 6 40 59 13 0 40 60 -32768]
)
(instance rm135 of KQ6Room
	(properties
		picture 135
		style $0007
	)
	
	(method (init)
		(super init: &rest)
		(theIconBar disable:)
		(Seagull init:)
		(Bset 59)
		(water init: setCycle: Forward)
		(water2 init: setCycle: Forward)
		(water3 init: setCycle: Forward)
		(if (== deathReason 35)
			(theMusic number: 921 setLoop: 1 play:)
			(self setScript: gnomesDeath)
		else
			(if (Btst 79) (= local0 1))
			(localMusic number: 920 setLoop: 1 play:)
			(self setScript: egoDrowns)
		)
	)
)

(instance gnomesDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					init:
					normal: 0
					view: 4543
					setLoop: 0
					cycleSpeed: 9
					moveSpeed: 0
					posn: 153 -10
					setPri: 15
					setStep: 15 12
					ignoreHorizon: 1
					illegalBits: 0
					ignoreActors: 1
					setCycle: Forward
					setMotion: MoveTo 140 155 self
				)
			)
			(1
				(localMusic number: 923 setLoop: 1 play:)
				(ego setCel: 0 setLoop: 1 setCycle: CycleTo 4 1 self)
			)
			(2 (ego setCycle: EndLoop self))
			(3
				(ego setLoop: 3 setCel: 0)
				(= seconds 4)
			)
			(4 (messager say: 1 0 4 0 self))
			(5
				(localMusic number: 920 setLoop: 1 play:)
				(ego
					setScale: Scaler 100 26 159 80
					setLoop: 2
					ignoreActors: 1
					ignoreHorizon: 1
					illegalBits: 0
					posn: 143 135
					moveSpeed: 0
					cycleSpeed: 8
					setStep: 3 2
					setMotion: MoveTo 140 83
					setCycle: EndLoop self
				)
			)
			(6 (ego setCycle: BegLoop self))
			(7
				(localMusic number: 920 setLoop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(8 (ego setCycle: BegLoop self))
			(9
				(localMusic number: 920 setLoop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(10 (= cycles 1))
			(11
				(localMusic number: 919 loop: -1 play:)
				(ego cel: 0 setLoop: 3 setCycle: EndLoop self)
			)
			(12
				(Seagull dispose:)
				(= cycles 2)
			)
			(13 (ego setCycle: BegLoop self))
			(14 (ego setCycle: EndLoop self))
			(15 (ego setCycle: BegLoop self))
			(16 (ego setCycle: EndLoop self))
			(17
				(ego setLoop: 3 setCycle: EndLoop self)
			)
			(18
				(Seagull dispose:)
				(= seconds 4)
			)
			(19 (ego hide:) (= cycles 2))
			(20
				(LoadMany 0 942)
				(Sounds eachElementDo: #stop)
				(EgoDead deathReason)
			)
		)
	)
)

(instance egoDrowns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local0 (genie init: setCycle: Forward cycleSpeed: 5))
				(localMusic flags: 1 number: 920 loop: -1 play:)
				(ego
					setScale: Scaler 100 26 159 80
					normal: 0
					view: 4543
					setLoop: 2
					ignoreActors: 1
					ignoreHorizon: 1
					illegalBits: 0
					posn: 143 135
					moveSpeed: 6
					cycleSpeed: 8
					init:
					setMotion: MoveTo 140 83
					setCycle: EndLoop self
				)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(if local0
					(messager say: 1 0 2 1 self)
				else
					(= cycles 1)
				)
			)
			(3
				(if local0
					(messager say: 1 0 2 2 self)
				else
					(messager say: 1 0 1 1 self)
				)
			)
			(4
				(if local0
					(messager say: 1 0 2 3 self)
				else
					(= cycles 1)
				)
			)
			(5
				(localMusic number: 920 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(6
				(localMusic number: 919 loop: -1 play:)
				(ego setCycle: BegLoop self)
			)
			(7
				(if local0
					(messager say: 1 0 2 4 self)
				else
					(messager say: 1 0 1 2 self)
				)
			)
			(8 (ego setCycle: EndLoop self))
			(9
				(ego cel: 0 setLoop: 3 setCycle: EndLoop self)
			)
			(10
				(Seagull dispose:)
				(ego hide:)
				(Sounds eachElementDo: #stop)
				(= cycles 2)
			)
			(11
				(LoadMany 0 942)
				(if local0 (EgoDead 21) else (EgoDead 34))
			)
		)
	)
)

(class Seagull of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 135
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 10
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
	(method (init)
		(super init: &rest)
		(self
			ignoreActors: 1
			ignoreHorizon: 1
			illegalBits: 0
			fly:
		)
	)
	
	(method (cue)
		(self fly:)
	)
	
	(method (fly)
		(switch (Random 1 4)
			(1
				(if (== local1 1)
					(self cue:)
				else
					(self setCycle: MoveCycle @local2 self)
					(= local1 1)
				)
			)
			(2
				(if (== local1 2)
					(self cue:)
				else
					(self setCycle: MoveCycle @local147 self)
					(= local1 2)
				)
			)
			(3
				(if (== local1 3)
					(self cue:)
				else
					(self setCycle: MoveCycle @local228 self)
					(= local1 3)
				)
			)
			(4
				(if (== local1 4)
					(self cue:)
				else
					(self setCycle: MoveCycle @local389 self)
					(= local1 4)
				)
			)
		)
	)
)

(instance water of Actor
	(properties
		x 135
		y 92
		view 136
		cycleSpeed 20
	)
)

(instance water2 of Actor
	(properties
		x 39
		y 101
		view 136
		loop 2
		cel 3
		cycleSpeed 20
	)
)

(instance water3 of Actor
	(properties
		x 281
		y 98
		view 136
		loop 1
		cel 1
		priority 6
		cycleSpeed 20
	)
)

(instance genie of Actor
	(properties
		x 70
		y 119
		view 262
		loop 2
	)
)

(instance localMusic of Kq6Sound
	(properties)
)
