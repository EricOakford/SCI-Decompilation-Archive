;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use Waters)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm003 0
)

(local
	local0
	local1
	[theX 6] = [232 189 6 72 189 80]
	[local8 38] = [135 131 304 131 304 189 212 189 211 166 219 161 231 157 230 153 223 150 172 163 163 156 140 164 124 150 115 150 100 150 75 150 81 142 116 140 133 136]
	[local46 38] = [116 133 70 134 70 130 62 128 49 123 40 123 32 117 32 109 38 103 92 88 97 78 91 71 72 62 55 58 48 54 48 5 319 5 319 126 127 126]
	[local84 18] = [0 183 0 131 14 132 45 155 62 155 64 159 48 159 51 164 53 184]
	[local102 24] = [0 15 45 15 44 49 46 55 60 62 75 66 86 78 80 88 55 93 24 93 26 97 0 102]
	[local126 10] = [93 158 106 159 114 163 97 168 72 167]
	[local136 9] = [1000 32 110 4 11 24 19 23 30]
)
(instance rm003 of KQ5Room
	(properties
		picture 3
		horizon 65
		north 2
		east 4
		west 6
	)
	
	(method (init)
		(super init:)
		(theAudio number: 7777 doNotStop: 1 loop: -1 play:)
		(theMusic number: 29 loop: -1 play:)
		(Load VIEW 48)
		(cedric init:)
		(if (== (theGame detailLevel:) 3) (water init:))
		(wheel init: setCycle: Forward)
		(cow init: setScript: cowScript)
		(smoke init: setCycle: Forward)
		(= global325 3025)
		(switch prevRoomNum
			(west
				(ego view: 6 posn: 4 118)
				(cedric setScript: cedricLand)
			)
			(north
				(ego view: 42 posn: 79 68)
				(cedric setScript: cedricLand)
			)
			(else 
				(HandsOff)
				(ego view: 6 posn: 150 128)
				(self setScript: enterFromTown)
			)
		)
		(self setFeatures: townPath town1 town2 river mountains)
		(ego normal: 1 setStep: 1 1 illegalBits: -32768 init:)
		((ego head?) hide:)
		(poly1 points: @local8 size: 19)
		(poly2 points: @local46 size: 19)
		(poly3 points: @local84 size: 9)
		(poly4 points: @local102 size: 12)
		(poly5 points: @local126 size: 5)
		(self addObstacle: poly1 poly2 poly3 poly4 poly5)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= local0 (self edgeToRoom: (ego edgeHit?)))
				)
				(self setScript: cedricFly)
			)
			((& (= temp0 (ego onControl: 0)) $4000) (self setScript: enterTown))
			((& temp0 $2000) (HandsOff) (self setScript: drownHim))
			((< (ego y?) 95) (ego view: 42))
			((> (ego y?) 95) (ego view: 6))
		)
	)
	
	(method (dispose)
		(DisposeScript 401)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (event claimed?) (not (== (event type?) 16384)))
			(return)
		)
	)
	
	(method (newRoom n)
		(theMusic2 fade:)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance cedricLand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cedric cel: 0 setCycle: EndLoop self)
			)
			(1
				(cedric stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance cedricFly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (cedric setCycle: BegLoop self))
			(1
				(theMusic fade:)
				(curRoom newRoom: local0)
			)
		)
	)
)

(instance drownHim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc762_1 @local136 3032 self)
			)
			(1
				(DoAudio 1 8068)
				(= cycles 1)
			)
			(2
				(ego
					normal: 0
					view: 48
					setPri: 15
					loop: (if (< (ego loop?) 4) 0 else 1)
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(theAudio number: 8068 loop: 1 play:)
				(cls)
				(ego
					setLoop: 2
					posn:
						(if (== (ego loop?) 0)
							(+ (ego x?) 15)
						else
							(- (ego x?) 23)
						)
						(+ (ego y?) 9)
					setCycle: Forward
					setStep: 2 1
					setMotion: PolyPath -5 200
					cycleSpeed: 2
				)
				(= cycles 1)
			)
			(4
				(if (!= (DoAudio 6) -1)
					(-- state)
				else
					(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
				)
				(= cycles 1)
			)
			(5
				(if (> (ego x?) -5) (-- state))
				(= cycles 1)
			)
			(6 (= seconds 3))
			(7
				(= deathMessage 179)
				(EgoDead)
			)
		)
	)
)

(instance enterFromTown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 110 138 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterTown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 29))
					(Bset 29)
					(proc762_1 @local136 3026 self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(ego setMotion: MoveTo 150 128 self)
			)
			(2
				(HandsOn)
				(curRoom newRoom: 4)
			)
		)
	)
)

(instance cowScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cow cycleSpeed: 2 setCycle: BegLoop)
				(= seconds (Random 5 10))
				(= state -1)
			)
		)
	)
)

(instance town1 of RFeature
	(properties
		nsTop 18
		nsLeft 165
		nsBottom 107
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 180)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance town2 of RFeature
	(properties
		nsTop 59
		nsLeft 103
		nsBottom 151
		nsRight 224
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 180)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance river of RFeature
	(properties)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bt       code_0884
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      16384
			eq?     
			not     
			bnt      code_0888
code_0884:
			ret     
			jmp      code_0915
code_0888:
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			pushi    3
			pushi    4
			dup     
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    OnControl,  6
			push    
			ldi      8192
			and     
			bnt      code_08b1
			ldi      2
code_08b1:
			eq?     
			bnt      code_08c7
			pushi    1
			pushi    181
			callb    SpeakAudio,  2
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0914
code_08c7:
			dup     
			pushi    3
			pushi    4
			dup     
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    OnControl,  6
			push    
			ldi      8192
			and     
			bnt      code_08e8
			ldi      3
code_08e8:
			eq?     
			bnt      code_0914
			pushi    0
			callb    HandsOff,  0
			pushi    #setMotion
			pushi    3
			class    PolyPath
			push    
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			lag      ego
			send     10
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_0914:
			toss    
code_0915:
			ret     
		)
	)
)

(instance mountains of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0400))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 182)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance townPath of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 183)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wheel of Prop
	(properties
		x 280
		y 167
		view 150
		priority 14
		signal $0010
		cycleSpeed 2
		detailLevel 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 184)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cow of Prop
	(properties
		x 145
		y 151
		view 150
		loop 1
		priority 9
		signal $0010
		detailLevel 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 185)
					(event claimed: 1)
				)
				(verbTalk
					(SpeakAudio 187)
					(event claimed: 1)
				)
				(verbDo
					(proc762_1 @local136 3028)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cedric of Prop
	(properties
		x 42
		y 129
		view 141
		loop 4
		cel 13
		priority 14
		signal $4010
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 186)
					(event claimed: 1)
				)
				(verbTalk
					(if (not local1)
						(++ local1)
						(proc762_1 @local136 3029)
					else
						(switch (Random 0 3)
							(0 (SpeakAudio 75))
							(1 (SpeakAudio 76))
							(2 (SpeakAudio 77))
							(else  (SpeakAudio 78))
						)
					)
					(event claimed: 1)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(proc762_1 @local136 3019)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 202
		y 20
		view 150
		loop 2
		cycleSpeed 5
		detailLevel 3
	)
)

(instance water of Waters
	(properties
		x 1000
		y 1000
		view 148
		priority 1
		cycleSpeed 5
		detailLevel 3
	)
	
	(method (getLoop)
		(= x [theX pos])
		(= y [theX (++ pos)])
		(= cel [theX (++ pos)])
	)
	
	(method (saveLoop)
		(= [theX pos] cel)
		(++ pos)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)

(instance poly5 of Polygon
	(properties
		type $0002
	)
)
