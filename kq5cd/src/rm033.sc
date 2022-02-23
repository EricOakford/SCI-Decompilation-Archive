;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm033 0
)

(local
	local0
	[local1 46] = [0 42 95 42 100 60 115 67 101 76 53 79 73 92 125 92 114 96 80 100 58 101 65 103 117 109 116 117 77 122 52 122 60 132 99 148 120 155 101 161 120 167 57 189 0 189]
	[local47 54] = [0 0 319 0 319 146 245 132 187 133 151 146 118 152 100 146 68 133 155 117 168 105 118 107 83 103 84 101 113 98 133 94 144 89 73 90 60 81 61 80 95 80 133 68 118 67 102 59 98 45 104 39 0 39]
	[local101 14] = [234 157 243 158 248 164 223 173 200 164 205 147 225 147]
	[local115 9] = [1028 211 76 3 9 26 24 32 32]
)
(instance rm033 of KQ5Room
	(properties
		picture 33
		horizon 67
		north 32
		east 34
	)
	
	(method (init)
		(super init:)
		(if (== globalCedric 100)
			(theMusic2 number: 106 loop: -1 playBed:)
			(Load VIEW 520)
			(Load SOUND 83)
			(self setScript: sledRun)
		else
			(switch prevRoomNum
				(north
					(ego view: 16 posn: 116 71 loop: 4 cel: 2 init:)
					((ego head?) hide:)
					(HandsOff)
					(self setScript: walkFromNorth)
				)
				(else 
					(ego
						posn: 312 165
						init:
						normal: 1
						view: 12
						setLoop: -1
						setCel: -1
						setStep: 2 2
						moveSpeed: (theGame egoMoveSpeed?)
						cycleSpeed: 0
						setCycle: KQ5SyncWalk
					)
					(sled init:)
				)
			)
		)
		(poly1 points: @local1 size: 23)
		(poly2 points: @local47 size: 27)
		(poly3 points: @local101 size: 7)
		(self
			setFeatures: crevasse path33 path33a bridge mountains
			addObstacle: poly1 poly2 poly3
		)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
			((& (= temp1 (ego onControl: 0)) $1080)
				(ego
					priority: (- (ego priority?) 2)
					signal: (| (ego signal?) $0010)
					loop: 1
				)
				(self setScript: falling)
			)
			((& temp1 $2000)
				(ego
					priority: (ego priority?)
					signal: (| (ego signal?) $0010)
					loop: 1
				)
				(self setScript: falling)
			)
			((& temp1 $0020)
				(ego
					priority: 8
					signal: (| (ego signal?) $0010)
					loop: 0
				)
				(bridge priority: 15 signal: (| (bridge signal?) $0010))
				(self setScript: falling)
			)
			((& temp1 $0004)
				(bridge cycleSpeed: 1 setCycle: EndLoop)
				(ego
					priority: (- (ego priority?) 1)
					signal: (| (ego signal?) $0010)
				)
				(self setScript: falling)
			)
			((& temp1 $0300)
				(ego
					priority: (cond 
						((< (ego y?) 68) 1)
						((< (ego y?) 88) 2)
						(else 4)
					)
					signal: (| (ego signal?) $0010)
					loop: 0
				)
				(self setScript: falling)
			)
			((& temp1 $0008) (ego view: 16) ((ego head?) hide:))
			((& temp1 $0010) (ego view: 14) ((ego head?) show:))
			((& temp1 $0040) (ego view: 12))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance sledRun of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					normal: 0
					ignoreHorizon: 1
					view: 520
					loop: 0
					cel: 2
					posn: 1 39
					init:
					moveSpeed: 0
					illegalBits: 0
					setMotion: MoveTo 73 40 self
				)
				((ego head?) hide:)
			)
			(1
				(ego setLoop: 1 setCel: 0 setMotion: MoveTo 95 49 self)
			)
			(2
				(ego setCel: 1 posn: 95 49 setMotion: MoveTo 106 61 self)
			)
			(3
				(ego setCel: 2 posn: 106 61 setMotion: MoveTo 130 71 self)
			)
			(4
				(ego setCel: 4 posn: 130 71 setMotion: MoveTo 112 77 self)
			)
			(5
				(ego
					setLoop: 2
					setCel: 0
					posn: 112 77
					setMotion: MoveTo 54 80 self
				)
			)
			(6
				(ego setCel: 1 posn: 54 80 setMotion: MoveTo 82 88 self)
			)
			(7
				(ego posn: 82 88 setMotion: MoveTo 103 91 self)
			)
			(8
				(ego setCel: 2 posn: 103 91 setMotion: MoveTo 137 91 self)
			)
			(9
				(ego setCel: 3 posn: 137 91 setMotion: MoveTo 96 100 self)
			)
			(10
				(ego
					setLoop: 3
					setCel: 0
					posn: 96 100
					setMotion: MoveTo 64 103 self
				)
			)
			(11
				(ego setCel: 1 setMotion: MoveTo 101 108 self)
			)
			(12
				(ego setCel: 2 setMotion: MoveTo 143 118 self)
			)
			(13
				(ego setCel: 3 setMotion: MoveTo 158 119 self)
			)
			(14
				(theMusic2 number: 107 loop: 1 playBed:)
				(ego setLoop: 4 setCel: 0 setMotion: MoveTo 179 121 self)
			)
			(15
				(ego
					setStep: 4 4
					setCel: 1
					setMotion: MoveTo 196 143 self
				)
			)
			(16
				(ego setCel: 2 setMotion: MoveTo 219 175 self)
			)
			(17
				(ego setCel: 3 setMotion: MoveTo 221 175 self)
			)
			(18
				(ego
					setLoop: 5
					setCel: 0
					posn: 224 157
					setCycle: EndLoop self
				)
				(sled init:)
				(theAudio number: 8790 loop: 1 play: self)
				(theMusic3 number: 108 loop: 1 play:)
			)
			(19 (= seconds 4))
			(20)
			(21
				(ego
					posn: 226 152
					setLoop: 9
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(22
				(proc762_1 @local115 7022 self)
			)
			(23
				(HandsOn)
				(ego
					normal: 1
					view: 12
					setLoop: -1
					setCel: -1
					x: (+ (ego x?) 3)
					setStep: 2 2
					moveSpeed: (theGame egoMoveSpeed?)
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					loop: 11
					cel: 4
					illegalBits: -32768
				)
				((ego head?) show:)
				(sled stopUpd:)
				(client setScript: 0)
				(= globalCedric 0)
			)
		)
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (IsObject (bridge cycler?))
					(= cycles 3)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				((ego head?) hide:)
				(ego
					normal: 0
					view:
					(switch (ego view?)
						(16 82)
						(14 80)
						(else  78)
					)
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
					illegalBits: 0
				)
				(theAudio number: 7053 play:)
				(theMusic3 number: 83 priority: 15 loop: 1 play:)
			)
			(2
				(ego
					setLoop: (ego loop?)
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230 self
				)
			)
			(3 (= seconds 3))
			(4
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance walkFromNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 3) self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance path33 of RFeature
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
					(SpeakAudio 445)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path33a of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0800))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 446)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance crevasse of RFeature
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
					(SpeakAudio 447)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 450)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bridge of Prop
	(properties
		x 75
		y 145
		view 516
		priority 8
		signal $4010
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 447)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 450)
					(event claimed: 1)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(29
							(SpeakAudio 452)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 453)
							(event claimed: 1)
						)
					)
				)
			)
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
				(not (& (OnControl 4 (event x?) (event y?)) $4000))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 448)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance sled of Actor
	(properties
		x 226
		y 172
		view 520
		loop 7
		priority 10
		signal $4010
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 449)
					(event claimed: 1)
				)
				(verbDo
					(if (not local0)
						(++ local0)
						(SpeakAudio 451)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties)
)

(instance poly2 of Polygon
	(properties)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)
