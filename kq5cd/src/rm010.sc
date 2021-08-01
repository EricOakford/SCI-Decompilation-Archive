;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm010 0
)

(local
	[local0 18] = [0 142 97 142 113 151 107 161 115 168 108 179 95 182 95 189 0 189]
	[local18 8] = [153 189 120 139 319 133 319 189]
	[local26 24] = [319 101 250 99 268 65 254 65 262 52 245 51 245 48 248 44 264 35 278 35 288 0 319]
	[local50 40] = [0 0 128 0 127 34 161 29 250 26 242 53 240 88 205 90 204 93 200 96 180 98 142 98 137 90 121 88 119 96 69 100 56 90 35 93 27 85 0 93]
	local90
	local91
	gEgoIllegalBits
	[local93 12] = [0 9027 0 9028 1 9029 0 9030 1 9031]
	[local105 20] = [1 9018 0 9019 1 9020 0 9021 1 9022 1 9023 0 9024 1 9025 1 9026]
	[local125 9] = [1001 171 48 4 11 23 28 22 36]
	[local134 9] = [1003 204 38 4 11 25 23 31 31]
)
(instance rm010 of KQ5Room
	(properties
		picture 10
		horizon 70
		north 9
		east 7
		south 27
		west 12
	)
	
	(method (init)
		(super init:)
		(= global320 195)
		(= global321 36)
		(= global325 3044)
		(self setRegions: 202)
		(theMusic number: 24 loop: -1 play:)
		(= gEgoIllegalBits (ego illegalBits?))
		(switch prevRoomNum
			(west
				(ego posn: 15 110)
				(self setScript: (ScriptID 202 1))
			)
			(east
				(ego posn: 310 110)
				(self setScript: (ScriptID 202 1))
			)
			(north
				(ego posn: 246 75)
				(self setScript: (ScriptID 202 1))
			)
			(south
				(ego posn: 150 186)
				(self setScript: (ScriptID 202 1))
			)
			(else  (ego posn: 5 110))
		)
		(ego view: 0 illegalBits: -32768 ignoreHorizon: 1 init:)
		(if (!= ((inventory at: 9) owner?) 9)
			(= local90 1)
			(addToPics add: wTree)
		)
		(addToPics add: stool doit:)
		(if (!= ((inventory at: 15) owner?) 10)
			(self setFeatures: RPuppet)
			(theMusic2 number: 21 loop: -1 play:)
			(if (== (theGame detailLevel:) 3)
				(oldGnome init: setScript: smoke)
			else
				(oldGnome init: stopUpd:)
			)
			(youngGnome init: setScript: boyScript)
		)
		(self setFeatures: stool wind door house lake)
		(poly1 points: @local0 size: 9)
		(poly2 points: @local18 size: 4)
		(poly3 points: @local26 size: 12)
		(poly4 points: @local50 size: 20)
		(self addObstacle: poly1 poly2 poly3 poly4)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(self setScript: (ScriptID 202 2))
			)
		)
	)
	
	(method (dispose)
		(ego illegalBits: gEgoIllegalBits)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(theMusic2 fade:)
		(super newRoom: n)
	)
)

(instance smoke of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oldGnome cycleSpeed: 2 cel: 0 loop: 0 setCycle: End)
				(= seconds (Random 5 10))
				(if (< (Random 0 100) 50) (= state 1))
			)
			(1
				(oldGnome loop: 7 cel: 0 setCycle: End)
				(= seconds (Random 5 10))
				(if (< (Random 0 100) 50) (= state -1))
			)
			(2
				(oldGnome loop: 8 cel: 0 setCycle: End)
				(= seconds (Random 5 10))
				(if (< (Random 0 100) 50) (= state 0))
			)
			(3
				(oldGnome loop: 10 cel: 0 setCycle: End)
				(= seconds (Random 5 10))
			)
			(4
				(oldGnome loop: 9 cel: 0 setCycle: End)
				(= seconds (Random 5 10))
				(if (< (Random 0 100) 50) (= state 3))
			)
			(5
				(oldGnome loop: 10 cel: 5 setCycle: Beg self)
				(= state -1)
			)
		)
	)
)

(instance giveWheel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 22 loop: -1 play:)
				(ego setMotion: PolyPath 228 96 self)
			)
			(1
				(Face ego oldGnome 5)
				(oldGnome
					cycleSpeed: 0
					loop: 3
					cel: 0
					setCycle: End self
					setScript: 0
				)
			)
			(2
				(oldGnome loop: 4 cel: 0 setCycle: End self)
			)
			(3
				(oldGnome loop: 6 cel: 0 setCycle: End self)
			)
			(4
				(oldGnome setCycle: Beg self)
			)
			(5
				(proc762_0 @local134 @local125 @local105 self)
			)
			(6
				(youngGnome
					setScript: 0
					loop: 4
					cel: 0
					setCycle: End self
				)
				(= cycles 1)
			)
			(7
				(youngGnome loop: 5 cel: 0 setCycle: End)
				(= cycles 1)
			)
			(8
				(youngGnome loop: 6 cel: 0 setCycle: End self)
			)
			(9
				(ego setMotion: MoveTo 170 100 self)
			)
			(10
				(Face ego youngGnome 5)
				(youngGnome loop: 7 cel: 0 setCycle: End self)
				(RPuppet dispose:)
				(ego get: 12)
			)
			(11
				(cls)
				(youngGnome loop: 8 cel: 0 setCycle: End self)
			)
			(12 (= cycles 1))
			(13
				(ego setLoop: 0)
				((ego head?) setCel: 1)
				(youngGnome
					view: 282
					cycleSpeed: 1
					setCycle: Walk
					illegalBits: 0
					ignoreActors: 1
					moveSpeed: 1
					setMotion: MoveTo 340 110 self
				)
				(oldGnome
					view: 282
					setCycle: Walk
					illegalBits: 0
					moveSpeed: 1
					cycleSpeed: 1
					setLoop: 2
					setMotion: MoveTo 360 110 self
				)
			)
			(14)
			(15
				(theMusic2 fade:)
				(oldGnome dispose:)
				(youngGnome dispose:)
				(ego setLoop: -1)
				((ego head?) cel: -1)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance boyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(youngGnome
					loop: 0
					cycleSpeed: 2
					cel: 0
					setCycle: End self
				)
			)
			(1
				(youngGnome loop: 1 cel: 0 setCycle: End self)
				(= state (- (Random 0 3) 1))
			)
			(2
				(youngGnome loop: 2 cel: 0 setCycle: End self)
				(= state (- (Random 0 3) 1))
			)
			(3
				(youngGnome loop: 3 cel: 0 setCycle: End self)
				(= state (- (Random 0 3) 1))
			)
		)
	)
)

(instance converse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 229 99 self)
			)
			(1
				(Face ego oldGnome 5)
				((ego head?) cel: 6)
				(oldGnome setScript: 0)
				(= cycles 2)
			)
			(2
				(proc762_0 @local134 @local125 @local93 self)
			)
			(3
				(cls)
				(HandsOn)
				(gnomeHead dispose:)
				(oldGnome setScript: smoke)
				(client setScript: 0)
			)
		)
	)
)

(instance shutTight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local91
					(ego setMotion: PolyPath 179 100 self)
				else
					(ego setMotion: PolyPath 130 95 self)
				)
			)
			(1 (ego loop: 3) (= cycles 2))
			(2
				(if local91 (SpeakAudio 246) else (SpeakAudio 247))
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance door of RFeature
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
				(JOY_UPRIGHT
					(SpeakAudio 238)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (cast contains: oldGnome)
						(proc762_1 @local125 9032)
					else
						(HandsOff)
						(= local91 0)
						(curRoom setScript: shutTight)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(1
							(SpeakAudio 251)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance wind of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 239)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (cast contains: oldGnome)
						(SpeakAudio 248)
					else
						(HandsOff)
						(= local91 1)
						(curRoom setScript: shutTight)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance house of RFeature
	(properties
		nsTop 36
		nsLeft 116
		nsBottom 86
		nsRight 195
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
				(JOY_UPRIGHT
					(SpeakAudio 240)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance RPuppet of RFeature
	(properties
		nsTop 78
		nsLeft 148
		nsBottom 94
		nsRight 163
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
				(JOY_UPRIGHT
					(SpeakAudio 241)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 249)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance gnomeHead of Prop
	(properties
		x 215
		y 89
		z 17
		view 284
		loop 2
	)
)

(instance oldGnome of Actor
	(properties
		x 214
		y 88
		view 284
		signal $4000
		detailLevel 3
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
				(JOY_UPRIGHT
					(SpeakAudio 242)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc762_1 @local125 9033)
					(event claimed: 1)
				)
				(JOY_DOWN
					(if (not (Btst 10))
						(Bset 10)
						(curRoom setScript: converse)
					else
						(proc762_1 @local125 9035)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(15
							(event claimed: 1)
							(ego put: 15 10)
							(HandsOff)
							(SolvePuzzle 4)
							(curRoom setScript: giveWheel)
						)
						(28 (event claimed: 0))
						(else 
							(proc762_1 @local125 9034)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance youngGnome of Actor
	(properties
		x 163
		y 96
		view 274
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
				(MousedOn RPuppet event)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 243)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 250)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 253)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 252)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance stool of RPicView
	(properties
		x 164
		y 93
		view 282
		loop 1
	)
)

(instance wTree of RPicView
	(properties
		x 256
		y 16
		view 282
		loop 1
		cel 1
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
				(JOY_UPRIGHT (event claimed: 1))
			)
		)
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

(instance lake of RFeature
	(properties
		nsTop 1
		nsLeft 214
		nsBottom 20
		nsRight 293
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
				(JOY_UPRIGHT
					(if (== local90 1)
						(SpeakAudio 244)
					else
						(SpeakAudio 245)
					)
					(event claimed: 1)
				)
			)
		)
	)
)
