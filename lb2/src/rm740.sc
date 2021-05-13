;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include sci.sh)
(use Main)
(use LBRoom)
(use PFollow)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Feature)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm740 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
)
(instance rm740 of LBRoom
	(properties
		noun 9
		picture 740
		north 480
		west 730
	)
	
	(method (init)
		(curRoom obstacles: (List new:))
		((ScriptID 2740 0) doit: (curRoom obstacles?))
		(ego
			init:
			normalize: 732
			ignoreActors: 1
			actions: egoActions
		)
		(theGame handsOff:)
		(LoadMany 128 734 742 812 745)
		(LoadMany 132 736 52)
		(Palette palSET_INTENSITY 0 255 0)
		(super init:)
		(sFXRats play:)
		(theIconBar disable: 7)
		(= local0 1)
		(steve init:)
		(rat1 init: setCycle: RTRandCycle)
		(rat2 init: setCycle: RTRandCycle)
		(rat3 init: setCycle: RTRandCycle)
		(rat4 init: setCycle: RTRandCycle)
		(rat5 init: setCycle: RTRandCycle)
		(floor init: setOnMeCheck: 1 8)
		(wall init: setOnMeCheck: 1 16)
		(furnaceExit init:)
		(tRexExit init:)
		(directionHandler add: self)
		(walkHandler add: self)
		(self setScript: sEnterSouth)
	)
	
	(method (doit)
		(super doit:)
		(if local0
			(Palette palSET_INTENSITY 0 255 (= local1 (+ local1 2)))
			(if (>= local1 100)
				(= local0 0)
				(steve setScript: sGunShots)
			)
		)
		(cond 
			(script)
			((IsObjectOnControl ego 2) (self setScript: sBurnEm))
			((IsObjectOnControl ego 4) (self setScript: sGoTRex))
			((and (> (ego x?) 211) (== (ego view?) 732)) (ego view: 745 cycleSpeed: 6 moveSpeed: 6 xStep: 3))
			((and (< (ego x?) 212) (== (ego view?) 745)) (ego view: 732 cycleSpeed: 4 moveSpeed: 4 xStep: 2))
		)
	)
	
	(method (dispose)
		(LoadMany 0 930 991)
		(directionHandler delete: self)
		(walkHandler delete: self)
		(DisposeScript 2740)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				(
					(and
						(& (event type?) evJOYSTICK)
						(== (theIconBar curIcon?) (theIconBar walkIconItem?))
						(or
							(== (event message?) JOY_LEFT)
							(== (event message?) JOY_UPLEFT)
							(== (event message?) JOY_DOWNLEFT)
						)
					)
					(event claimed: 1)
				)
				((& (event type?) evMOVE)
					(if (< mouseX (ego x?))
						(event claimed: 1)
					else
						(return 0)
					)
				)
				(else (return 0))
			)
		)
	)
	
	(method (cue)
		(theMusic2 number: 16 loop: -1 flags: 1 play:)
	)
)

(instance sGunShots of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sFX number: 52 flags: 1 play: self)
			)
			(1 (= ticks 30))
			(2 (sFX play: self))
			(3
				(messager say: 8 0 0 0 curRoom)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(steve
					setLoop: 0
					setCycle: Walk
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setMotion: PFollow ego 36
				)
				(ego
					setLoop: 0
					posn: 10 209
					edgeHit: 0
					setCycle: Walk
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setMotion: MoveTo 89 163 self
				)
			)
			(1
				(rat3 setScript: sRat3Movement)
				(self dispose:)
			)
		)
	)
)

(instance sBurnEm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 744
					setLoop: 2
					setCel: 0
					ignoreActors: 1
					cycleSpeed: 12
					setCycle: End self
				)
				(steve ignoreActors: 1)
			)
			(1
				(theMusic2 stop:)
				(sFXFall play:)
				(ego
					setPri: 5
					moveSpeed: 3
					yStep: 4
					setCycle: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 15) self
				)
			)
			(2
				(ego dispose:)
				(steve setMotion: PolyPath 239 106 self)
			)
			(3
				(sFXFall play:)
				(steve view: 744 setLoop: 1 setCel: 0 setCycle: End self)
			)
			(4
				(steve
					setPri: 5
					moveSpeed: 3
					yStep: 4
					setMotion: MoveTo (steve x?) (+ (steve y?) 40) self
				)
			)
			(5 (= ticks 120))
			(6
				(theMusic number: 736 flags: 1 loop: 1 play:)
				(furnaceExit setCycle: Osc 1 self)
			)
			(7 (= ticks 120))
			(8
				(theMusic number: 736 flags: 1 loop: 1 play:)
				(furnaceExit setCycle: CT 4 1 self)
			)
			(9
				(furnaceExit setCycle: Beg self)
			)
			(10 (= seconds 3))
			(11
				(= deathReason 2)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sGoTRex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 745
					setLoop: 1
					ignoreActors: 1
					cycleSpeed: 10
					setCycle: End self
				)
				(steve ignoreActors: 1)
			)
			(1
				(steve setMotion: PolyPath 290 106 self)
			)
			(2
				(steve
					view: 745
					setLoop: 2
					cycleSpeed: 10
					setCycle: End self
					setMotion: MoveTo (ego x?) (- (ego y?) 2)
				)
			)
			(3
				(steve dispose:)
				(= cycles 2)
			)
			(4
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance sRat3Movement of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(rat3
					loop: 1
					setCycle: Walk
					cycleSpeed: 6
					setMotion: MoveTo (- (rat3 x?) 30) (+ (rat3 y?) 16) self
				)
			)
			(2
				(rat3
					setMotion: MoveTo (+ (rat3 x?) 30) (- (rat3 y?) 16) self
				)
			)
			(3
				(rat3 setLoop: 3 cycleSpeed: 12 setCycle: RTRandCycle)
				(rat4 setScript: sRat4Movement)
				(self dispose:)
			)
		)
	)
)

(instance sRat4Movement of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat4
					loop: 1
					setCycle: Walk
					cycleSpeed: 6
					setMotion: MoveTo (- (rat4 x?) 36) (+ (rat4 y?) 21) self
				)
			)
			(1
				(rat4
					setMotion: MoveTo (+ (rat4 x?) 36) (- (rat4 y?) 21) self
				)
			)
			(2
				(rat4 setLoop: 3 cycleSpeed: 12 setCycle: RTRandCycle)
				(rat5 setScript: sRat5Movement)
				(self dispose:)
			)
		)
	)
)

(instance sRat5Movement of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat5
					loop: 1
					setCycle: Walk
					cycleSpeed: 6
					setMotion: MoveTo (- (rat5 x?) 40) (+ (rat5 y?) 22) self
				)
			)
			(1
				(rat5
					setMotion: MoveTo (+ (rat5 x?) 40) (- (rat5 y?) 22) self
				)
			)
			(2
				(rat5 setLoop: 3 cycleSpeed: 12 setCycle: RTRandCycle)
				(= seconds 4)
			)
			(3
				(rat1
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 51 182
				)
				(rat2
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 59 172
				)
				(rat3
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 66 175
				)
				(rat4
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 76 165
				)
				(rat5
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 84 165
				)
				(self dispose:)
			)
		)
	)
)

(instance sKillLaura of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 3 loop: 1 flags: 1 play:)
				(= local5 1)
				(rat1 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat2 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat3
					setLoop: 9
					setPri: 15
					setMotion: MoveTo (rat3 x?) (- (rat3 y?) 24) self
				)
				(rat4 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat5 setLoop: 3 setCycle: Fwd setMotion: 0)
			)
			(1
				(ego
					view: 734
					setLoop: 0
					setCel: 0
					cycleSpeed: 9
					setCycle: End
				)
				(rat3
					setLoop: 10
					setMotion: MoveTo (rat3 x?) (+ (rat3 y?) 24) self
				)
			)
			(2
				(rat1 setLoop: 1 setCycle: Walk setMotion: MoveTo 49 183)
				(rat2 setLoop: 1 setCycle: Walk setMotion: MoveTo 57 173)
				(rat3 setLoop: 1 setCycle: Walk setMotion: MoveTo 64 176)
				(rat4 setLoop: 1 setCycle: Walk setMotion: MoveTo 74 166)
				(rat5 setLoop: 1 setCycle: Walk setMotion: MoveTo 82 166)
				(self dispose:)
			)
		)
	)
)

(instance sKillSteve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local6 1)
				(rat1 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat2 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat3
					setMotion: MoveTo (- (rat3 x?) 7) (+ (rat3 y?) 4) self
				)
				(rat4 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat5 setLoop: 3 setCycle: Fwd setMotion: 0)
			)
			(1
				(rat3
					setLoop: 9
					setPri: 15
					setMotion: MoveTo (rat3 x?) (- (rat3 y?) 18) self
				)
			)
			(2
				(steve
					view: 734
					setLoop: 1
					setCel: 0
					cycleSpeed: 9
					ignoreActors: 1
					setCycle: End
				)
				(rat3
					setLoop: 10
					setMotion: MoveTo (rat3 x?) (+ (rat3 y?) 18) self
				)
			)
			(3
				(rat3 setLoop: 3 setCycle: Fwd)
				(theMusic2 fade: self)
				(= seconds 20)
			)
			(4
				(if seconds (= seconds 0))
				(= deathReason 8)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sRatsEatCheese of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rat1 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat2 setLoop: 3 setCycle: Fwd setMotion: 0)
				(if (rat3 script?)
					(rat3 setScript: sRat3Return)
				else
					(rat3 setLoop: 3 setCycle: Fwd setMotion: 0)
				)
				(if (rat4 script?)
					(rat4 setScript: sRat4Return)
				else
					(rat4 setLoop: 3 setCycle: Fwd setMotion: 0)
				)
				(if (rat5 script?)
					(rat5 setScript: sRat5Return)
				else
					(rat5 setLoop: 3 setCycle: Fwd setMotion: 0)
				)
				(ego
					view: 742
					setLoop: 0
					setCel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(1
				(ego setCycle: Beg)
				(cheese
					init:
					posn: (+ (ego x?) 30) (- (ego y?) 31)
					setMotion: JumpTo (- (rat2 x?) 2) (+ (rat2 y?) 1) self
				)
			)
			(2
				(ego put: 16)
				((ScriptID 21 1) doit: 785)
				(= ticks 90)
			)
			(3
				(messager say: 4 27)
				(cheese dispose:)
				(ego view: 732 setLoop: 0 setCel: 0)
				(rat1
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 51 182
				)
				(rat2
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 59 172
				)
				(rat3
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 66 175
				)
				(rat4
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 76 165
				)
				(rat5
					setLoop: 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 84 165
				)
				(self dispose:)
			)
		)
	)
)

(instance sRat3Return of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat3 setMotion: MoveTo 177 111 self)
			)
			(1
				(rat3 setLoop: 3 setCycle: Fwd)
				(self dispose:)
			)
		)
	)
)

(instance sRat4Return of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat4 setMotion: MoveTo 187 101 self)
			)
			(1
				(rat4 setLoop: 3 setCycle: Fwd)
				(self dispose:)
			)
		)
	)
)

(instance sRat5Return of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat5 setMotion: MoveTo 195 101 self)
			)
			(1
				(rat5 setLoop: 3 setCycle: Fwd)
				(self dispose:)
			)
		)
	)
)

(instance sRatsEnterFurnace of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rat1 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat2 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat3 setLoop: 3 setScript: 0 setCycle: Fwd setMotion: 0)
				(rat4 setLoop: 3 setScript: 0 setCycle: Fwd setMotion: 0)
				(rat5 setLoop: 3 setScript: 0 setCycle: Fwd setMotion: 0)
				(ego
					view: 742
					setLoop: 0
					setCel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(1
				(ego setCycle: Beg)
				(cheese
					init:
					posn: (+ (ego x?) 30) (- (ego y?) 31)
					moveSpeed: 6
					setPri: 5
					setMotion: JumpTo 245 108 self
				)
			)
			(2
				(cheese dispose:)
				(ego view: 732 setLoop: 0 setCycle: Walk put: 16)
				((ScriptID 21 1) doit: 785)
				(rat1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 194 105 self
				)
			)
			(3
				(rat1 setLoop: 4 setMotion: MoveTo 211 104 self)
			)
			(4
				(rat1 setLoop: 6 cycleSpeed: 6 setCycle: Osc 1 self)
			)
			(5
				(rat1
					setCycle: 0
					setPri: 5
					setMotion: JumpTo 239 114 self
				)
			)
			(6
				(rat1 dispose:)
				(rat2
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 190 107 self
				)
			)
			(7
				(rat2
					setCycle: Fwd
					setPri: 5
					setMotion: JumpTo 238 117 self
				)
			)
			(8
				(rat2 dispose:)
				(rat3
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 194 105 self
				)
			)
			(9
				(rat3 setLoop: 4 setMotion: MoveTo 228 98 self)
			)
			(10
				(rat3 setLoop: 6 cycleSpeed: 6 setCycle: Osc 1 self)
			)
			(11
				(rat3
					setPri: 5
					moveSpeed: 0
					yStep: 4
					setMotion: MoveTo 228 110 self
				)
			)
			(12
				(rat3 dispose:)
				(rat4
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 194 105 self
				)
			)
			(13
				(rat4 setLoop: 4 setMotion: MoveTo 211 104 self)
			)
			(14
				(rat4
					setCycle: Fwd
					setPri: 5
					setMotion: JumpTo 246 94 self
				)
			)
			(15
				(rat4 setLoop: 2 setCel: 7 setCycle: 0 moveSpeed: 9)
				(= ticks 45)
			)
			(16
				(rat4 setMotion: MoveTo 246 110 self)
			)
			(17
				(rat4 dispose:)
				(rat5
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 190 107 self
				)
			)
			(18
				(rat5
					setLoop: 8
					cycleSpeed: 9
					setCycle: Fwd
					setPri: 5
					setMotion: JumpTo 238 117 self
				)
			)
			(19
				(rat5 dispose:)
				(sFXRats fade:)
				(messager say: 7 0 8)
				(= local2 1)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sRatsEnterRex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rat1 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat2 setLoop: 3 setCycle: Fwd setMotion: 0)
				(rat3 setLoop: 3 setScript: 0 setCycle: Fwd setMotion: 0)
				(rat4 setLoop: 3 setScript: 0 setCycle: Fwd setMotion: 0)
				(rat5 setLoop: 3 setScript: 0 setCycle: Fwd setMotion: 0)
				(ego
					view: 742
					setLoop: 0
					setCel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(1
				(ego setCycle: Beg)
				(cheese
					init:
					posn: (+ (ego x?) 30) (- (ego y?) 31)
					moveSpeed: 6
					setPri: 5
					setMotion: JumpTo 295 105 self
				)
			)
			(2
				(cheese dispose:)
				(ego view: 732 setLoop: 0 setCycle: Walk put: 16)
				((ScriptID 21 1) doit: 785)
				(rat1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 194 105 self
				)
			)
			(3
				(rat1 setLoop: 4 setMotion: MoveTo 266 103 self)
			)
			(4
				(rat1 setLoop: 6 cycleSpeed: 6 setCycle: Osc 1 self)
			)
			(5
				(rat1
					setCycle: 0
					setPri: 5
					setMotion: JumpTo 291 112 self
				)
			)
			(6
				(rat1 dispose:)
				(rat2
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 190 107 self
				)
			)
			(7
				(rat2
					setCycle: Fwd
					setPri: 5
					setMotion: JumpTo 291 112 self
				)
			)
			(8
				(rat2 dispose:)
				(rat3
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 194 105 self
				)
			)
			(9
				(rat3 setLoop: 4 setMotion: MoveTo 266 103 self)
			)
			(10
				(rat3 setLoop: 4 setMotion: MoveTo 281 98 self)
			)
			(11
				(rat3 setLoop: 6 cycleSpeed: 6 setCycle: Osc 1 self)
			)
			(12
				(rat3
					setPri: 5
					moveSpeed: 0
					yStep: 4
					setMotion: MoveTo 281 111 self
				)
			)
			(13
				(rat3 dispose:)
				(rat4
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 194 105 self
				)
			)
			(14
				(rat4 setLoop: 4 setMotion: MoveTo 271 103 self)
			)
			(15
				(rat4
					setCycle: Fwd
					setPri: 5
					setMotion: JumpTo 299 94 self
				)
			)
			(16
				(rat4 setLoop: 2 setCel: 7 setCycle: 0 moveSpeed: 9)
				(= ticks 45)
			)
			(17
				(rat4 setMotion: MoveTo 299 119 self)
			)
			(18
				(rat4 dispose:)
				(rat5
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 190 107 self
				)
			)
			(19
				(rat5
					setLoop: 8
					cycleSpeed: 9
					setCycle: Fwd
					setPri: 5
					setMotion: JumpTo 283 112 self
				)
			)
			(20
				(rat5 dispose:)
				(sFXRats fade:)
				(messager say: 7 0 9)
				(= local2 1)
				(Bset 46)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(27 (messager say: 6 27) 1)
			(else  0)
		)
	)
)

(instance steve of Actor
	(properties
		x -10
		y 222
		noun 5
		view 733
		signal $4000
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			(
			(and (> (self x?) 211) (== (self view?) 733))
				(self
					view: 812
					cycleSpeed: 6
					moveSpeed: 6
					xStep: 3
					scaleSignal: 1
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 1 1 27 0 0 1887)
			)
			(4
				(messager say: 1 4 27 0 0 1887)
			)
			(8
				(messager say: 1 8 27 0 0 1887)
			)
			(2
				(if local2
					(messager say: 5 2 7)
				else
					(switch local4
						(0 (messager say: 5 2))
						(else  (messager say: 5 2 6))
					)
					(++ local4)
				)
			)
			(27
				(ego put: 16)
				((ScriptID 21 1) doit: 785)
				(messager say: 5 27)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cheese of Actor
	(properties
		yStep 1
		view 742
		loop 2
		xStep 1
		moveSpeed 9
	)
)

(instance rat1 of Actor
	(properties
		x 162
		y 118
		noun 4
		view 741
		loop 3
		signal $4000
		cycleSpeed 18
		moveSpeed 4
	)
	
	(method (doVerb)
		(rat3 doVerb: &rest)
	)
)

(instance rat2 of Actor
	(properties
		x 170
		y 108
		noun 4
		view 741
		loop 3
		signal $4000
		cycleSpeed 18
		moveSpeed 4
	)
	
	(method (doVerb)
		(rat3 doVerb: &rest)
	)
)

(instance rat3 of Actor
	(properties
		x 177
		y 111
		noun 4
		view 741
		loop 3
		signal $4000
		cycleSpeed 18
		moveSpeed 4
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			(
				(and
					(< (self distanceTo: ego) 5)
					(== (ego view?) 732)
					(not local5)
				)
				(curRoom setScript: sKillLaura)
			)
			(
				(and
					(< (self distanceTo: steve) 10)
					(== (steve view?) 733)
					(not local6)
				)
				(curRoom setScript: sKillSteve)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(switch local3
					(0 (messager say: 4 1 3))
					(1 (messager say: 4 1 4))
					(else  (messager say: 4 1 5))
				)
				(++ local3)
			)
			(27
				(curRoom setScript: sRatsEatCheese)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rat4 of Actor
	(properties
		x 187
		y 101
		noun 4
		view 741
		loop 3
		signal $4000
		cycleSpeed 18
		moveSpeed 4
	)
	
	(method (doVerb)
		(rat3 doVerb: &rest)
	)
)

(instance rat5 of Actor
	(properties
		x 195
		y 101
		noun 4
		view 741
		loop 3
		signal $4000
		cycleSpeed 18
		moveSpeed 4
	)
	
	(method (doVerb)
		(rat3 doVerb: &rest)
	)
)

(instance furnaceExit of Prop
	(properties
		x 253
		y 33
		noun 3
		view 744
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (< (ego x?) 202)
					(messager say: 3 1 1)
				else
					(messager say: 3 1 2)
				)
			)
			(27
				(if local2
					(super doVerb: theVerb)
				else
					(curRoom setScript: sRatsEnterFurnace)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tRexExit of Feature
	(properties
		x 287
		y 69
		noun 2
		nsTop 35
		nsLeft 271
		nsBottom 103
		nsRight 304
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (< (ego x?) 202)
					(messager say: 2 1 1)
				else
					(messager say: 2 1 2)
				)
			)
			(27
				(if local2
					(super doVerb: theVerb)
				else
					(curRoom setScript: sRatsEnterRex)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance floor of Feature
	(properties
		y 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(27
				(if local2
					(super doVerb: theVerb)
				else
					(curRoom setScript: sRatsEatCheese)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance wall of Feature
	(properties
		y 1
		noun 1
	)
)

(instance sFXRats of Sound
	(properties
		flags $0001
		number 733
		loop -1
	)
)

(instance sFXFall of Sound
	(properties
		flags $0001
		number 542
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
