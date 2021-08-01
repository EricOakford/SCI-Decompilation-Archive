;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Path)
(use Motion)
(use Actor)
(use System)

(public
	rm090 0
)

(local
	[local0 7] = [0 94 36 115 68 126 -32768]
	[local7 15] = [68 126 99 124 146 108 165 96 181 81 193 71 201 62 -32768]
	local22
	[local23 16] = [0 168 92 168 143 152 173 150 137 0 319 0 319 189 0 189]
	[local39 8] = [0 0 134 0 125 141 0 151]
)
(procedure (localproc_000e)
	(cls)
	(h1Mouth cue:)
	(h2Mouth cue:)
	(h3Mouth cue:)
	(h4Mouth cue:)
)

(instance rm090 of KQ5Room
	(properties
		picture 90
		west 50
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 836 838 54 70)
		(switch prevRoomNum
			(91
				(Load rsVIEW 840)
				(harpy1 setScript: argueScript)
			)
			(50
				(if (Btst 55) (theMusic number: 814 loop: -1 playBed:))
				(ego
					init:
					view: (if (Btst 74) 659 else 2)
					posn: 40 162
					setStep: 2 2
				)
				(if (== (ego view?) 659) ((ego head?) hide:))
				(if (Btst 54)
					(harpy1 init: posn: -50 20 setScript: harpyInitScript)
				)
			)
			(else 
				(Load rsVIEW 840)
				(theMusic number: 816 loop: -1 playBed:)
				(self setScript: crtn5Script)
			)
		)
		(if (not (ego has: 31))
			(fishhook init: setScript: hookScript)
		)
		(self setFeatures: island room addObstacle: poly1 poly2)
		(poly1 points: @local23 size: 8)
		(poly2 points: @local39 size: 4)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((= temp0 (self edgeToRoom: (ego edgeHit?))) (curRoom newRoom: temp0))
			((& (ego onControl: 0) $0010) (HandsOff) (self setScript: fallScript))
		)
	)
	
	(method (dispose)
		(DisposeScript 983)
		(DisposeScript 941)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
			(
				(and
					(== (event type?) 16384)
					(MousedOn ego event)
					(== (event message?) JOY_DOWNRIGHT)
				)
				(switch (inventory indexOf: (theIconBar curInvIcon?))
					(10
						(curRoom setScript: playHarpScript)
						(event claimed: 1)
					)
					(28 (event claimed: 0))
					(else 
						(SpeakAudio 753)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance harpyInitScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (> (ego x?) 88)
			(HandsOff)
			(curRoom setScript: harpyScript)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (Random 1 100) 50) 0 else (self dispose:))
			)
		)
	)
)

(instance harpyScript of Script
	(properties)
	
	(method (doit)
		(if state
			(ego posn: (- (harpy1 x?) 5) (- (harpy1 y?) 3))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 815 loop: -1 playBed: 90)
				(harpy1
					view: 836
					setLoop: 0
					ignoreActors: 1
					setCycle: Fwd
					setMotion: MoveTo (ego x?) (- (ego y?) 30) self
				)
			)
			(1
				(if (IsObject (ego head?)) ((ego head?) dispose:))
				(ego normal: 0 view: 838 setLoop: 0 setCycle: Fwd)
				(harpy1 setMotion: MoveTo 350 20 self)
			)
			(2
				(= global103 0)
				(= cycles 1)
			)
			(3
				(= deathMessage 744)
				(EgoDead 247 0 End)
			)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					normal: 0
					view: (if (Btst 74) 838 else 70)
					setLoop: (if (Btst 74) 5 else 0)
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					setPri: 1
					illegalBits: 0
				)
				(theAudio number: 7053 play:)
				(theMusic2 number: 83 loop: 1 play:)
			)
			(1
				(ego
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230 self
				)
			)
			(2
				(= global103 0)
				(theAudio number: 8068 play:)
				(= seconds 2)
			)
			(3
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance crtn5Script of Script
	(properties)
	
	(method (doit)
		(if (not state)
			(ego posn: (- (harpy4 x?) 5) (harpy4 y?))
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(DrawCel 2 0 0 400 0 0 3)
				(harpy2 init:)
				(harpy3 init:)
				(h3Mouth init:)
				(ego
					view: 838
					setLoop: 0
					x: -100
					setCycle: Fwd
					normal: 0
					init:
				)
				((ego head?) hide:)
				(harpy4 init: setCycle: Fwd setMotion: swoop1 self)
			)
			(1
				(harpy4 setMotion: swoop2 self)
				(ego
					posn: (+ (ego x?) 18) (+ (ego y?) 31)
					setLoop: 1
					setCel: 0
					cycleSpeed: 2
					setCycle: End self
				)
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?))
			)
			(2)
			(3
				(harpy1 init: setScript: harpy1Script)
				(= cycles 1)
			)
			(4
				(ego
					view: 2
					posn: (+ (ego x?) 29) (ego y?)
					setMotion: 0
					normal: 1
					loop: 7
					cel: 0
				)
				((ego head?) show:)
				(harpy4
					view: 840
					setLoop: 5
					setCel: 1
					setPri: 9
					setCycle: 0
				)
				(h4Mouth init:)
				(self dispose:)
			)
		)
	)
)

(instance harpy1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(harpy1 init: setCycle: Fwd setMotion: MoveTo 66 127 self)
			)
			(1
				(harpy1 setLoop: 3 setCel: 0 setCycle: End self)
			)
			(2
				(harpy1 setMotion: MoveTo 66 136 self)
			)
			(3
				(harpy1
					view: 840
					loop: 0
					posn: (+ (harpy1 x?) 3) (+ (harpy1 y?) 25)
				)
				(= seconds 3)
			)
			(4 (curRoom newRoom: 91))
		)
	)
)

(instance argueScript of Script
	(properties)
	
	(method (doit)
		(if (< (ego x?) (+ (harpy1 x?) 20))
			(HandsOff)
			(curRoom setScript: escapeScript)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOn)
				(= global103 0)
				(ego init:)
				(NormalEgo 11 2)
				(ego
					cel: 0
					setStep: 2 2
					posn: 126 156
					init:
					view: 2
					loop: 7
				)
				(harpy1 view: 840 posn: 69 161 setLoop: 0 setCel: 1 init:)
				(harpy2 init: setCel: 1)
				(harpy3 init:)
				(harpy4
					view: 840
					setLoop: 5
					setCel: 1
					posn: 201 62
					init:
					setPri: 9
				)
				(h1Mouth init:)
				(h2Mouth init:)
				(h3Mouth init:)
				(h4Mouth init:)
				(= cycles 1)
			)
			(1
				(h2Mouth cue: 1)
				(SpeakAudio 5060 self)
			)
			(2
				(localproc_000e)
				(h4Mouth cue: 1)
				(SpeakAudio 5061 self)
			)
			(3
				(localproc_000e)
				(h3Mouth cue: 1)
				(SpeakAudio 5062 self)
			)
			(4
				(localproc_000e)
				(h4Mouth cue: 1)
				(SpeakAudio 5063 self)
			)
			(5
				(localproc_000e)
				(h1Mouth cue: 1)
				(SpeakAudio 5064 self)
			)
			(6
				(localproc_000e)
				(h2Mouth cue: 1)
				(SpeakAudio 5065 self)
			)
			(7
				(localproc_000e)
				(h3Mouth cue: 1)
				(SpeakAudio 5066 self)
			)
			(8
				(localproc_000e)
				(h2Mouth cue: 1)
				(SpeakAudio 5067 self)
			)
			(9
				(localproc_000e)
				(h3Mouth cue: 1)
				(SpeakAudio 5068 self)
			)
			(10
				(localproc_000e)
				(h2Mouth cue: 1)
				(SpeakAudio 5069 self)
			)
			(11
				(localproc_000e)
				(h3Mouth cue: 1)
				(SpeakAudio 5070 self)
			)
			(12
				(localproc_000e)
				(h1Mouth cue: 1)
				(SpeakAudio 5071 self)
			)
			(13
				(localproc_000e)
				(HandsOff)
				(client setScript: 0)
				(curRoom setScript: grabEgoScript)
			)
		)
	)
)

(instance escapeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_000e)
				(harpy1 setScript: 0)
				(h1Mouth cue: 1)
				(SpeakAudio 5077 self)
			)
			(1
				(localproc_000e)
				(h1Mouth dispose:)
				(harpy1 view: 842 setLoop: 0 cel: 0 setCycle: End self)
			)
			(2
				(if (IsObject (ego head?)) ((ego head?) dispose:))
				(ego dispose:)
				(harpy1 setLoop: 2 cel: 0 setCycle: End self)
			)
			(3
				(harpy1
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo -30 (harpy1 y?)
				)
				(h2Mouth dispose:)
				(harpy2
					view: 836
					setLoop: 5
					ignoreActors:
					setCel: 0
					posn: (- (harpy2 x?) 25) (- (harpy2 y?) 20)
					setCycle: End self
				)
			)
			(4
				(harpy3 setScript: harpy3Script)
				(harpy4 setScript: harpy4Script self)
				(harpy2
					setCycle: Fwd
					setLoop: 2
					setMotion: MoveTo -40 140
				)
			)
			(5
				(= global103 0)
				(= cycles 2)
			)
			(6
				(= deathMessage 744)
				(EgoDead 247 0 End)
			)
		)
	)
)

(instance grabEgoScript of Script
	(properties)
	
	(method (doit)
		(if (> state 2)
			(ego posn: (- (harpy2 x?) 4) (- (harpy2 y?) 1))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_000e)
				(h2Mouth dispose:)
				(harpy2
					view: 836
					setLoop: 5
					ignoreActors:
					setCel: 0
					posn: (- (harpy2 x?) 25) (- (harpy2 y?) 20)
					setCycle: End self
				)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 838
					setLoop: 3
					posn: (- (ego x?) 18) (- (ego y?) 34)
					setCel: 0
					illegalBits: 0
					ignoreHorizon: 1
				)
			)
			(1
				(harpy2
					setLoop: 2
					setCycle: Fwd
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (+ (ego x?) 17) (+ (ego y?) 6) self
				)
			)
			(2 (ego setCycle: End self))
			(3
				(ego setLoop: 4 setCel: 0 setCycle: Fwd)
				(harpy2 setLoop: 2 setMotion: MoveTo -40 70 self)
			)
			(4
				(localproc_000e)
				(h1Mouth dispose:)
				(harpy1 setLoop: 4 setCycle: End self)
			)
			(5
				(harpy3 setScript: harpy3Script)
				(harpy4 setScript: harpy4Script self)
				(harpy1
					view: 836
					setLoop: 5
					setCel: 0
					posn: (- (harpy1 x?) 25) (- (harpy1 y?) 20)
					setCycle: End self
				)
			)
			(6
				(harpy1 setLoop: 2 setCycle: Fwd setMotion: MoveTo -40 70)
			)
			(7
				(= global103 0)
				(= cycles 2)
			)
			(8
				(= deathMessage 744)
				(EgoDead 247 0 End)
			)
		)
	)
)

(instance playHarpScript of Script
	(properties)
	
	(method (doit)
		(if (> state 3)
			(harp posn: (- (harpy2 x?) 1) (+ (harpy2 y?) 3))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 10)
				(SolvePuzzle 4)
				(HandsOff)
				(harpy1 setScript: 0)
				(localproc_000e)
				(note setScript: noteScript init:)
				(harp posn: (ego x?) (ego y?) 1000 init:)
				((ego head?) view: 54 loop: 1)
				(ego
					normal: 0
					view: 54
					loop: 0
					cel: 0
					cycleSpeed: (if (== howFast 0) 0 else 5)
					posn: (+ (ego x?) 1) (+ (ego y?) 2)
					setCycle: RandCycle 20 self
				)
				(theMusic number: 817 loop: -1 playBed:)
			)
			(1
				(ego setCycle: RandCycle)
				(h3Mouth cue: 1)
				(SpeakAudio 5072 self)
			)
			(2
				(localproc_000e)
				(h2Mouth cue: 1)
				(SpeakAudio 5073 self)
			)
			(3
				(localproc_000e)
				(h2Mouth dispose:)
				(harpy2
					view: 836
					setLoop: 5
					setCel: 0
					posn: (- (harpy2 x?) 25) (- (harpy2 y?) 20)
					setCycle: End self
				)
			)
			(4
				(harpy2
					setLoop: 2
					setCycle: Fwd
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (ego x?) (- (ego y?) 26) self
				)
			)
			(5
				(note dispose:)
				(ego setLoop: 2 cycleSpeed: 2 setCycle: End self)
				(theMusic stop:)
			)
			(6
				(harpy2 setMotion: MoveTo -40 70 self)
				(harp z: 0 setLoop: 3)
				(ego
					normal: 1
					view: 2
					illegalBits: -32768
					loop: 4
					setLoop: -1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					setStep: 2 2
					observeControl: 8
					observeControl: 2048
				)
				((ego head?) view: 2 loop: 4)
			)
			(7
				(h1Mouth cue: 1)
				(h3Mouth cue: 1)
				(h4Mouth cue: 1)
				(SpeakAudio 5074 self)
			)
			(8
				(localproc_000e)
				(h1Mouth dispose:)
				(harpy1 setLoop: 4 setCycle: End self)
			)
			(9
				(harpy3 setScript: harpy3Script)
				(harpy4 setScript: harpy4Script self)
				(harpy1
					view: 836
					setLoop: 5
					setCel: 0
					posn: (- (harpy1 x?) 25) (- (harpy1 y?) 20)
					setCycle: End self
				)
			)
			(10
				(harpy1 setLoop: 2 setCycle: Fwd setMotion: MoveTo -40 70)
			)
			(11
				(theMusic number: 814 loop: -1 playBed:)
				(Bset 55)
				(harpy1 dispose:)
				(harpy2 dispose:)
				(harpy3 dispose:)
				(harpy4 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance noteScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(note
					x: (+ (ego x?) 5)
					y: (- (ego y?) 20)
					setCel: (Random 0 3)
					setMotion: MoveTo (+ (ego x?) 25) (- (ego y?) 40) self
				)
			)
			(1 (self init:))
		)
	)
)

(instance harpy3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(h3Mouth dispose:)
				(harpy3
					view: 836
					setLoop: 6
					posn: (- (harpy3 x?) 3) (- (harpy3 y?) 20)
					setCycle: End self
				)
			)
			(1
				(harpy3
					setLoop: 2
					posn: (- (harpy3 x?) 35) (- (harpy3 y?) 19)
					setCycle: Fwd
					setMotion: MoveTo -40 10 self
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance harpy4Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 20))
			(1
				(h4Mouth dispose:)
				(harpy4
					view: 836
					setLoop: 6
					posn: (- (harpy4 x?) 3) (- (harpy4 y?) 20)
					setCycle: End self
				)
			)
			(2
				(harpy4
					setLoop: 2
					posn: (- (harpy4 x?) 35) (- (harpy4 y?) 19)
					setCycle: Fwd
					setMotion: MoveTo -40 10 self
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance wrongThingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_000e)
				(h3Mouth cue: 1)
				(SpeakAudio 5075 self)
			)
			(1
				(localproc_000e)
				(h4Mouth cue: 1)
				(SpeakAudio 5076 self)
			)
			(2
				(localproc_000e)
				(argueScript start: local22)
				(harpy1 setScript: argueScript)
				(= local22 1000)
				(HandsOn)
			)
		)
	)
)

(instance hookScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(fishhook setCycle: End self)
			)
			(2
				(fishhook setCycle: 0)
				(self init:)
			)
		)
	)
)

(instance getHookScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 665)
				(ego
					setMotion: PolyPath (+ (fishhook x?) 10) (+ (fishhook y?) 1) self
				)
			)
			(1
				(if (cast contains: harpy1)
					(SpeakAudio 751)
				else
					(SpeakAudio 750)
				)
				(= register (ego view?))
				(if (== (ego view?) 2) ((ego head?) hide:))
				(ego
					normal: 0
					view: 665
					setLoop: (if (Btst 74) 3 else 4)
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(2
				(SolvePuzzle 2)
				(fishhook dispose:)
				(ego setCycle: End self)
			)
			(3
				(ego
					normal: 1
					view: register
					setLoop: -1
					loop: 3
					setCycle: KQ5SyncWalk
					get: 31
				)
				(if (== (ego view?) 2) ((ego head?) show:))
				(= cycles 3)
			)
			(4
				(HandsOn)
				(= global103 0)
				(self dispose:)
			)
		)
	)
)

(instance harpy1 of Actor
	(properties
		x -10
		y 120
		yStep 7
		view 836
		priority 10
		signal $6810
		cycleSpeed 1
		illegalBits $0000
		xStep 7
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
				(JOY_UPRIGHT
					(cls)
					(SpeakAudio 745)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cls)
					(SpeakAudio 752)
					(event claimed: 1)
				)
				(JOY_DOWN
					(cls)
					(SpeakAudio 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance harpy2 of Actor
	(properties
		x 211
		y 152
		yStep 7
		view 840
		loop 9
		priority 12
		signal $6810
		cycleSpeed 1
		illegalBits $0000
		xStep 7
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
				(JOY_UPRIGHT
					(cls)
					(SpeakAudio 745)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cls)
					(SpeakAudio 752)
					(event claimed: 1)
				)
				(JOY_DOWN
					(cls)
					(SpeakAudio 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance harpy3 of Actor
	(properties
		x 180
		y 61
		yStep 7
		view 840
		loop 5
		priority 10
		signal $6810
		cycleSpeed 1
		illegalBits $0000
		xStep 7
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
			(harpy1 handleEvent: event)
			(switch (event message?)
				(JOY_UPRIGHT
					(cls)
					(SpeakAudio 745)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cls)
					(SpeakAudio 752)
					(event claimed: 1)
				)
				(JOY_DOWN
					(cls)
					(SpeakAudio 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance harpy4 of Actor
	(properties
		x -10
		y 125
		yStep 7
		view 836
		priority 11
		signal $6810
		cycleSpeed 1
		illegalBits $0000
		xStep 7
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
				(harpy1 species event)
				(JOY_UPRIGHT
					(cls)
					(SpeakAudio 745)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cls)
					(SpeakAudio 752)
					(event claimed: 1)
				)
				(JOY_DOWN
					(cls)
					(SpeakAudio 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance h1Mouth of Prop
	(properties
		view 840
		loop 1
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (+ (harpy1 x?) 4) (- (harpy1 y?) 28)
			priority: (+ (harpy1 priority?) 1)
		)
		(h1Arms init:)
	)
	
	(method (dispose)
		(self setCycle: 0)
		(h1Arms dispose:)
		(super dispose:)
	)
	
	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h1Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h1Arms setCycle: RandCycle)
		)
	)
)

(instance h2Mouth of Prop
	(properties
		view 840
		loop 10
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy2 x?) 9) (- (harpy2 y?) 18)
			priority: (+ (harpy2 priority?) 1)
		)
		(h2Arms init:)
	)
	
	(method (dispose)
		(self setCycle: 0)
		(h2Arms dispose:)
		(super dispose:)
	)
	
	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h2Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h2Arms setCycle: RandCycle)
		)
	)
)

(instance h3Mouth of Prop
	(properties
		view 840
		loop 6
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy3 x?) 6) (- (harpy3 y?) 20)
			priority: (+ (harpy3 priority?) 1)
		)
		(h3Arms init:)
	)
	
	(method (dispose)
		(self setCycle: 0)
		(h3Arms dispose:)
		(super dispose:)
	)
	
	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h3Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h3Arms setCycle: RandCycle)
		)
	)
)

(instance h4Mouth of Prop
	(properties
		view 840
		loop 6
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy4 x?) 7) (- (harpy4 y?) 21)
			priority: (+ (harpy4 priority?) 1)
		)
		(h4Arms init:)
	)
	
	(method (dispose)
		(self setCycle: 0)
		(h4Arms dispose:)
		(super dispose:)
	)
	
	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h4Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h4Arms setCycle: RandCycle)
		)
	)
)

(instance h1Arms of Prop
	(properties
		view 840
		loop 2
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (+ (harpy1 x?) 1) (- (harpy1 y?) 23)
			priority: (+ (harpy1 priority?) 1)
		)
	)
)

(instance h2Arms of Prop
	(properties
		view 840
		loop 11
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy2 x?) 9) (- (harpy2 y?) 19)
			priority: (+ (harpy2 priority?) 1)
		)
	)
)

(instance h3Arms of Prop
	(properties
		view 840
		loop 7
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy3 x?) 7) (- (harpy3 y?) 17)
			priority: (+ (harpy3 priority?) 1)
		)
	)
)

(instance h4Arms of Prop
	(properties
		view 840
		loop 8
		signal $6810
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy4 x?) 1) (- (harpy4 y?) 18)
			priority: (+ (harpy4 priority?) 1)
		)
	)
)

(instance swoop1 of Path
	(properties)
	
	(method (at param1)
		(return [local0 param1])
	)
)

(instance swoop2 of Path
	(properties)
	
	(method (at param1)
		(return [local7 param1])
	)
)

(instance harp of Prop
	(properties
		view 54
		loop 4
		signal $6800
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
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
				(JOY_UPRIGHT
					(if (cast contains: harpy2)
						(SpeakAudio 746)
					else
						(SpeakAudio 747)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(10
							(curRoom setScript: playHarpScript)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(cond 
								((not (cast contains: harpy2)) 0)
								((== local22 1000) (cls) (SpeakAudio 754) (event claimed: 1))
								(else
									(HandsOff)
									(= local22 (+ (argueScript state?) 1))
									(harpy1 setScript: wrongThingScript)
									(event claimed: 1)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance island of RFeature
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
					(SpeakAudio 748)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance fishhook of Prop
	(properties
		x 90
		y 160
		view 652
		loop 4
		signal $6000
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
				(JOY_UPRIGHT
					(SpeakAudio 749)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getHookScript)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance note of Actor
	(properties
		view 836
		loop 7
		signal $0800
		cycleSpeed 3
		detailLevel 3
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
