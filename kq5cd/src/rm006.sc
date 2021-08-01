;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Door)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use Follow)
(use Chase)
(use RFeature)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm006 0
)

(local
	local0
	local1
	local2
	[local3 20] = [0 153 79 154 108 153 140 155 192 153 197 156 267 153 319 153 319 189 0 189]
	[local23 26] = [0 40 253 40 264 121 289 124 298 128 302 134 283 140 231 145 77 146 77 105 72 105 72 145 0 145]
	[local49 18] = [319 145 302 145 302 139 304 134 299 127 287 119 266 119 250 0 319]
	[local67 9] = [1005 210 90 5 8 26 19 24 32]
	[local76 9] = [1000 30 60 4 11 24 19 23 30]
)
(procedure (localproc_1562 &tmp newEvent)
	(signCel init:)
	(RedrawCast)
	(while
		(not
			(OneOf ((= newEvent (Event new:)) type?) 1 4 256)
		)
		(newEvent dispose:)
	)
	(newEvent dispose:)
	(signCel dispose:)
)

(instance rm006 of KQ5Room
	(properties
		picture 6
		north 7
		east 3
		west 27
	)
	
	(method (init)
		(super init:)
		(= global320 46)
		(= global321 121)
		(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
		(ego normal: 1 setStep: 2 1 view: 2 init:)
		(smoke setCycle: Fwd init:)
		(stream1 setCycle: Fwd init:)
		(stream2 setCycle: Fwd init:)
		(stream3 setCycle: Fwd init:)
		(stream4 setCycle: Fwd init:)
		(stream5 setCycle: Fwd init:)
		(= global325 3030)
		(self
			setFeatures: bakeSign road wind logs house
			setRegions: 202
			addObstacle: poly1 poly2 poly3
		)
		(poly1 points: @local3 size: 10)
		(poly2 points: @local23 size: 13)
		(poly3 points: @local49 size: 9)
		(switch prevRoomNum
			(west
				(ego posn: 7 152)
				(self setScript: (ScriptID 202 1))
			)
			(east
				(ego posn: 316 150)
				(self setScript: (ScriptID 202 1))
			)
			(north
				(ego posn: 264 121 priority: 1 init:)
				((ego head?) priority: 1)
				(HandsOff)
				(self setScript: room6WalkIn)
			)
			(206
				(ego posn: 116 150)
				(HandsOff)
				(self setScript: outDoor)
			)
			(else  (ego posn: 316 150))
		)
		(if
		(and (or (ego has: 8) (ego has: 16)) (not (Btst 83)))
			(rat setLoop: 6 ignoreActors: 1 init:)
			(cat setLoop: 2 ignoreActors: 1 init:)
		)
		(Load rsVIEW 71)
		(theMusic2 number: 24 loop: -1 play:)
		(door init:)
		(bakeSign init:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((& (ego onControl: 0) $0040) (HandsOff) (self setScript: walkTo7))
			((= temp0 (self edgeToRoom: (ego edgeHit?)))
				((ScriptID 202 2) register: (ego edgeHit?))
				(self setScript: (ScriptID 202 2))
			)
			((& (ego onControl: 1) $4000) (HandsOff) (ego setMotion: 0) (self setScript: openDoor))
			((& (ego onControl: 0) $0002) (HandsOff) (self setScript: drownHim))
			(
				(and
					(cast contains: rat)
					(> (ego x?) 290)
					(> (ego y?) 142)
				)
				(User canControl: 0)
				(ego setMotion: 0)
				(Bset 83)
				(theMusic3 number: 882 loop: -1 play:)
				(self setScript: catAndMouse)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 972)
		(DisposeScript 971)
		(DisposeScript 991)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(or
				script
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 208)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setMotion: PolyPath (ego x?) 189)
					(event claimed: 1)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (!= n 27) (theMusic fade:))
		(super newRoom: n)
	)
)

(instance openDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 73 146 self)
			)
			(1
				(ego cel: 1)
				(theAudio stop: doNotStop: 0)
				(proc762_1 @local76 3031 self)
			)
			(2
				(door cycleSpeed: 2 setCycle: End self)
			)
			(3
				(ego
					setPri: 11
					setMotion: MoveTo (ego x?) (- (ego y?) 7) self
				)
			)
			(4
				(door setPri: 12 setCycle: Beg self)
			)
			(5
				(theMusic fade:)
				(theMusic2 fade:)
				(curRoom newRoom: 206)
			)
		)
	)
)

(instance outDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego normal: 0 setPri: 11 setLoop: 2 posn: 73 141)
				(door setPri: 12 cycleSpeed: 2 setCycle: End self)
			)
			(1
				(ego
					setLoop: -1
					normal: 1
					setMotion: MoveTo (ego x?) (+ (ego y?) 9) self
				)
			)
			(2
				(door setPri: -1 setCycle: Beg)
				(= cycles 16)
			)
			(3
				(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
				(ego setPri: -1)
				(door stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkTo7 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 3) self
				)
			)
			(1
				(HandsOn)
				(curRoom newRoom: 7)
				(self dispose:)
			)
		)
	)
)

(instance drownHim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc762_1 @local76 3032 self)
			)
			(1
				(DoAudio 1 8068)
				(= cycles 1)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 71
					cel: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 5)
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(theAudio number: 8068 loop: 1 play:)
				(ego
					setLoop: 2
					posn: (- (ego x?) 30) (+ (ego y?) 10)
					setCycle: Fwd
					setStep: 3 1
					setMotion: MoveTo -15 169
					cycleSpeed: 3
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
				(if (> (ego x?) -15) (-- state))
				(= cycles 1)
			)
			(6 (= seconds 3))
			(7
				(cls)
				(= deathMessage 179)
				(EgoDead)
			)
		)
	)
)

(instance shoeFly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= local2 0) (flyShoe setCycle: Walk))
				(flyShoe setMotion: Chase cat 5 self)
			)
			(1
				(catAndMouse changeState: 6)
				(flyShoe
					setStep: 3 3
					setMotion: JumpTo (+ (flyShoe x?) 20) (+ (flyShoe y?) 20) self
				)
			)
			(2
				(flyShoe setScript: 0 dispose:)
			)
		)
	)
)

(instance catAndMouse of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 1) (not local1) (== (cat cel?) 6))
			(rat dispose:)
		)
		(if (and (== state 4) (== (ego cel?) 5))
			(flyShoe
				setLoop: (if (== local2 2) 14 else 11)
				illegalBits: 0
				ignoreActors: 1
				ignoreHorizon: 1
				x: (- (ego x?) 15)
				y: (- (ego y?) 19)
				setScript: shoeFly
				init:
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theAudio stop:)
				(curRoom setFeatures: catStrip)
				(SpeakAudio 207)
				(rat setCycle: Walk setMotion: MoveTo 330 151)
				(cat setCycle: Walk setMotion: Chase rat 10 self)
			)
			(1
				(cls)
				(if local1
					(cat cel: 0 setLoop: 4 setCycle: CT 8 1)
					(rat hide:)
				else
					(= local0 1)
					(cat cel: 0 setLoop: 4 setCycle: End self)
					(rat dispose:)
				)
				(theMusic3 stop:)
			)
			(2
				(cat
					setLoop: 5
					setCycle: Walk
					setMotion: MoveTo -20 151 self
				)
			)
			(3
				(HandsOn)
				(client setScript: 0)
				(cat dispose:)
				(self dispose:)
			)
			(4
				(cls)
				(HandsOff)
				((ego head?) hide:)
				(ego
					view: 221
					normal: 0
					loop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
				(cat setMotion: Follow rat 10)
			)
			(5)
			(6
				(theMusic3 stop:)
				((ego head?) show:)
				(ego
					normal: 1
					view: 2
					x: (- (ego x?) 11)
					loop: 1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(cat setLoop: 3 setCycle: Walk setMotion: MoveTo -20 151)
				(rat setMotion: MoveTo (- (ego x?) 25) (ego y?) self)
				(theAudio number: 7063 loop: 1 play:)
			)
			(7
				(theMusic4 number: 63 loop: 1 play:)
				(rat setLoop: 8)
				(= cycles 5)
			)
			(8
				(proc762_1 @local67 5103 self)
			)
			(9 (= cycles 1))
			(10
				(theMusic4 fade:)
				(cls)
				(= cycles 1)
			)
			(11
				(rat setLoop: 7 setMotion: MoveTo -20 151 self)
			)
			(12
				(rat dispose:)
				(= cycles 5)
			)
			(13
				(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance moveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: End self))
			(1 (= cycles 10))
			(2 (client setCycle: Beg self))
			(3
				(= state -1)
				(= seconds (Random 3 10))
			)
		)
	)
)

(instance room6WalkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 202 4)
					view: 138
					loop: 2
					cel: 0
					cycleSpeed: 3
					setCycle: End
				)
				(= cycles 2)
			)
			(1
				(ego setPri: -1 setMotion: PolyPath 301 136 self)
				((ego head?) setPri: -1)
			)
			(2
				(HandsOn)
				((ScriptID 202 4) setScript: (ScriptID 202 3))
				(self dispose:)
			)
		)
	)
)

(class StreamProp of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
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
		palette 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
	)
	
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
					(SpeakAudio 208)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setMotion: PolyPath (ego x?) 189)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance stream1 of StreamProp
	(properties
		x 194
		y 175
		view 220
		priority 5
		signal $4010
		cycleSpeed 6
		detailLevel 3
	)
)

(instance stream2 of StreamProp
	(properties
		x 113
		y 174
		view 220
		loop 1
		priority 5
		signal $4010
		cycleSpeed 6
		detailLevel 3
	)
)

(instance stream3 of StreamProp
	(properties
		x 13
		y 175
		view 220
		loop 9
		priority 5
		signal $4010
		cycleSpeed 6
		detailLevel 3
	)
)

(instance stream4 of StreamProp
	(properties
		x 59
		y 167
		view 220
		loop 10
		priority 5
		signal $4010
		cycleSpeed 5
		detailLevel 3
	)
)

(instance stream5 of StreamProp
	(properties
		x 289
		y 174
		view 220
		loop 13
		priority 5
		signal $4010
		cycleSpeed 5
		detailLevel 3
	)
)

(instance road of RFeature
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
					(SpeakAudio 209)
					(event claimed: 1)
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
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 210)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance logs of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 211)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance house of RFeature
	(properties
		nsTop 34
		nsLeft 9
		nsBottom 143
		nsRight 278
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
					(SpeakAudio 212)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door of Door
	(properties
		x 85
		y 143
		view 221
		signal $4001
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
					(SpeakAudio 213)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: openDoor)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance signCel of Prop
	(properties
		x 100
		y 100
		view 221
		loop 6
		priority 15
		signal $0010
	)
)

(instance bakeSign of RFeature
	(properties
		nsTop 106
		nsLeft 95
		nsBottom 116
		nsRight 109
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
					(localproc_1562)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cat of Actor
	(properties
		x -80
		y 151
		view 220
		loop 2
		xStep 4
	)
	
	(method (doit)
		(super doit:)
		(= nsLeft (+ nsLeft 5))
		(= nsRight (+ nsRight 5))
		(= nsTop (+ nsTop 5))
		(= nsBottom (+ nsBottom 5))
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
					(SpeakAudio 214)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if local0 (SpeakAudio 215) else (SpeakAudio 216))
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(8
							(if local0
								(SpeakAudio 215)
							else
								(= local1 1)
								(ego put: 8 6)
								(SolvePuzzle 4)
								(catAndMouse changeState: 4)
							)
							(event claimed: 1)
						)
						(16
							(if local0
								(SpeakAudio 215)
							else
								(SolvePuzzle 4)
								(= local2 1)
								(= local1 1)
								(ego put: 16 6)
								(catAndMouse changeState: 4)
							)
							(event claimed: 1)
						)
						(19
							(if local0
								(SpeakAudio 215)
							else
								(= local2 3)
								(= local1 1)
								(ego put: 19 6)
								(catAndMouse changeState: 4)
							)
							(event claimed: 1)
						)
						(5
							(if local0
								(SpeakAudio 215)
							else
								(= local2 2)
								(= local1 1)
								(ego put: 5 6)
								(catAndMouse changeState: 4)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance catStrip of RFeature
	(properties
		nsBottom 200
		nsRight 320
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
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(8
							(if local0
								(SpeakAudio 215)
							else
								(= local1 1)
								(ego put: 8 6)
								(SolvePuzzle 4)
								(catAndMouse changeState: 4)
							)
							(event claimed: 1)
						)
						(16
							(if local0
								(SpeakAudio 215)
							else
								(= local2 1)
								(= local1 1)
								(ego put: 16 6)
								(catAndMouse changeState: 4)
							)
							(event claimed: 1)
						)
						(5
							(if local0
								(SpeakAudio 215)
							else
								(= local2 2)
								(= local1 1)
								(ego put: 5 6)
								(catAndMouse changeState: 4)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance rat of Actor
	(properties
		x 38
		y 151
		view 220
		loop 6
		xStep 2
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
					(SpeakAudio 214)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance flyShoe of Actor
	(properties
		view 220
		loop 11
		priority 13
		signal $4810
		xStep 12
	)
)

(instance hands of Prop
	(properties
		x 204
		y 93
		view 223
		loop 2
		cel 3
		priority 12
		signal $4010
	)
)

(instance theMouth of Prop
	(properties
		x 203
		y 69
		view 223
		loop 3
		cel 2
		priority 12
		signal $4010
		cycleSpeed 3
	)
)

(instance theEyes of Prop
	(properties
		x 203
		y 63
		view 223
		loop 4
		cel 2
		priority 13
		signal $4010
	)
)

(instance eyebrows of Prop
	(properties
		x 203
		y 57
		view 223
		loop 5
		cel 1
		priority 13
		signal $4010
	)
)

(instance face of Prop
	(properties
		x 205
		y 110
		view 223
		priority 11
		signal $4010
	)
)

(instance cloud of Prop
	(properties
		x 205
		y 82
		view 214
		priority 10
		signal $4010
	)
)

(instance smoke of Prop
	(properties
		x 146
		y 5
		view 221
		loop 7
		cycleSpeed 5
		detailLevel 3
	)
)

(instance poly1 of Polygon
	(properties)
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
