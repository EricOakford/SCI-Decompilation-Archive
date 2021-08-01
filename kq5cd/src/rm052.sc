;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
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
	rm052 0
)

(local
	local0
	[local1 14] = [0 189 0 29 150 134 141 144 148 150 102 170 172 189]
	[local15 12] = [319 189 212 167 166 150 176 143 166 132 319 28]
	[local27 9] = [1000 236 90 4 11 24 19 23 30]
)
(instance rm052 of KQ5Room
	(properties
		picture 52
		horizon 120
		north 53
		south 51
	)
	
	(method (init)
		(super init:)
		(NormalEgo)
		(theMusic loop: -1 number: 832 vol: 127 play:)
		(if (not (Btst 55))
			(= global320 281)
			(= global321 152)
			(= global325 3083)
			(self setRegions: 202)
		)
		(self setFeatures: statue path52 theCastle)
		(ego
			view: 2
			normal: 1
			setStep: 2 2
			illegalBits: -32256
			init:
		)
		(switch prevRoomNum
			(north
				(ego posn: 159 138)
				(if (not (Btst 55))
					(self setScript: (ScriptID 202 1))
				)
			)
			(south
				(ego posn: 183 185)
				(if (not (Btst 55))
					(self setScript: (ScriptID 202 1))
				else
					(HandsOn)
				)
			)
			(else  (ego posn: 163 186))
		)
		(if (Btst 35)
			(lDragon view: 668 loop: 7 posn: 109 54 init:)
			(rDragon view: 668 loop: 8 posn: 209 51 init:)
			(lDragon cel: (lDragon lastCel:))
			(rDragon cel: (rDragon lastCel:))
			(lActor
				loop: 9
				cel: 2
				posn: 107 43
				setCycle: Fwd
				cycleSpeed: 1
				setPri: 15
				init:
			)
			(rActor
				loop: 10
				posn: 212 39
				setCycle: Fwd
				cycleSpeed: 1
				setPri: 15
				init:
			)
		else
			(lDragon setCycle: Fwd init: hide:)
			(rDragon setCycle: Fwd init: hide:)
			(Load rsVIEW 668)
		)
		(poly1 points: @local1 size: 7)
		(poly2 points: @local15 size: 6)
		(self addObstacle: poly1 poly2)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(if (not (Btst 35))
			(cond 
				((< (ego y?) 153)
					(if (not local0)
						(HandsOff)
						(self setScript: EgoBeingFried)
					)
				)
				((< (ego y?) 185)
					(= temp1 (/ (- 184 (ego y?)) 8))
					(lDragon
						loop: (* temp1 2)
						cycleSpeed: (- 3 temp1)
						show:
					)
					(rDragon
						loop: (+ (* temp1 2) 1)
						cycleSpeed: (- 3 temp1)
						show:
					)
				)
				(else (lDragon hide:) (rDragon hide:))
			)
		)
		(cond 
			(script (script doit:))
			((and (not (Btst 97)) (not (Btst 55))) (proc762_1 @local27 3084) (Bset 97))
			((and (!= (ego yStep?) 1) (<= (ego y?) 184)) (ego setStep: 2 1))
			((& (= temp2 (ego onControl: 0)) $0008)
				(if (not (Btst 55))
					((ScriptID 202 2) register: 1)
					(self setScript: (ScriptID 202 2))
				else
					(curRoom newRoom: 53)
				)
			)
			((& temp2 $1400) (self setScript: falling))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (not (Btst 55))
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				else
					(curRoom newRoom: temp0)
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
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
					(21
						(if (and (ego has: 21) (not (Btst 35)))
							(event claimed: 1)
							(self setScript: EgoUsingCrystal)
						)
					)
					(28 (event claimed: 0))
					(else 
						(SpeakAudio 559)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					normal: 0
					view: 70
					cel: 0
					setLoop: (if (& (ego onControl: 0) $0400) 0 else 1)
					setCycle: End self
				)
			)
			(1
				(theAudio number: 7053 play:)
				(= seconds 3)
			)
			(2 (EgoDead))
		)
	)
)

(instance EgoBeingFried of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 35)
				((ego head?) hide:)
				(theAudio number: 8130 loop: -1 play:)
				(ego view: 666 loop: 10 setCycle: End self cycleSpeed: 3)
				(lDragon loop: 8 posn: 157 48 cycleSpeed: 0 show:)
				(rDragon hide:)
			)
			(1
				(ego loop: 11 cel: 0 setCycle: End self)
			)
			(2
				(theAudio stop:)
				(lDragon loop: 6 posn: 117 57)
				(rDragon loop: 7 show:)
				(= cycles 1)
			)
			(3
				(lDragon cycleSpeed: (- 3 (/ (lDragon loop?) 2)))
				(rDragon cycleSpeed: (lDragon cycleSpeed?))
				(= cycles 10)
			)
			(4
				(if (lDragon loop?)
					(lDragon loop: (- (lDragon loop?) 2))
					(rDragon loop: (- (rDragon loop?) 2))
					(= state (- state 2))
				)
				(= cycles 1)
			)
			(5
				(lDragon hide:)
				(rDragon hide:)
				(= cycles 10)
			)
			(6
				(theMusic3 stop:)
				(= deathMessage 553)
				(EgoDead)
				(client setScript: 0)
			)
		)
	)
)

(instance EgoUsingCrystal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local0 1)
				(ego setMotion: MoveTo 160 150 self moveSpeed: 1)
				(Bset 22)
			)
			(1
				((ego head?) hide:)
				(egoTop
					posn: (ego x?) 130
					setCycle: End self
					cycleSpeed: 2
					init:
				)
				(ego view: 668 loop: 0)
			)
			(2
				(Bset 35)
				(theAudio number: 8130 loop: -1 play:)
				(lDragon
					view: 668
					loop: 2
					posn: 157 48
					cycleSpeed: 0
					show:
				)
				(rDragon hide:)
				(= seconds 3)
			)
			(3
				(theAudio stop:)
				(lDragon loop: 11 posn: 117 57 setCycle: Fwd)
				(rDragon view: 668 posn: 213 53 loop: 12 show:)
				(crystal setCycle: Fwd init:)
				(= cycles 20)
			)
			(4
				(theAudio number: 8131 loop: 1 play:)
				(crystal loop: 4 cel: 0 setCycle: End self)
			)
			(5
				(crystal loop: 5 setCycle: Fwd)
				(= cycles 20)
			)
			(6
				(theAudio number: 8791 loop: 1 play:)
				(lActor setMotion: MoveTo 109 54 self init:)
				(rActor setMotion: MoveTo 209 51 init:)
				(crystal loop: 3)
			)
			(7
				(lActor hide:)
				(rActor hide:)
				(lDragon
					view: 668
					loop: 7
					cel: 0
					posn: 109 54
					setCycle: End self
					cycleSpeed: 0
					show:
				)
				(rDragon
					view: 668
					loop: 8
					cel: 0
					posn: 209 51
					setCycle: End
					cycleSpeed: 0
					show:
				)
			)
			(8
				(lActor
					loop: 9
					cel: 2
					posn: 107 43
					setCycle: Fwd
					cycleSpeed: 3
					setPri: 15
					show:
				)
				(rActor
					loop: 10
					posn: 212 39
					setCycle: Fwd
					cycleSpeed: 3
					setPri: 15
					show:
				)
				(crystal hide: 3)
				(egoTop setCycle: Beg self)
			)
			(9
				(egoTop hide:)
				((ego head?) show:)
				(ego view: 2 loop: 7 cel: 3 moveSpeed: 0)
				(SolvePuzzle 5)
				(client setScript: 0)
				(Bclr 22)
				(HandsOn)
			)
		)
	)
)

(instance lDragon of Prop
	(properties
		x 117
		y 57
		view 666
	)
	
	(method (init)
		(super init:)
		(self setPri: 14)
	)
	
	(method (handleEvent event)
		(statue handleEvent: event)
	)
)

(instance rDragon of Prop
	(properties
		x 200
		y 52
		view 666
	)
	
	(method (init)
		(super init:)
		(self setPri: 14)
	)
	
	(method (handleEvent event)
		(statue handleEvent: event)
	)
)

(instance egoTop of Prop
	(properties
		view 668
		loop 1
	)
)

(instance crystal of Prop
	(properties
		x 160
		y 113
		view 668
		loop 3
		priority 15
		signal $0010
	)
)

(instance lActor of Actor
	(properties
		x 160
		y 106
		yStep 9
		view 668
		loop 6
		signal $6800
		illegalBits $0000
		xStep 12
	)
	
	(method (init)
		(super init:)
		(self setPri: 15)
	)
)

(instance rActor of Actor
	(properties
		x 160
		y 106
		yStep 9
		view 668
		loop 6
		signal $6800
		illegalBits $0000
		xStep 12
	)
	
	(method (init)
		(super init:)
		(self setPri: 15)
	)
)

(instance statue of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $2000))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(if (Btst 35) (SpeakAudio 554) else (SpeakAudio 555))
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 556)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 561)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(21
							(if (and (ego has: 21) (not (Btst 35)))
								(event claimed: 1)
								(rm052 setScript: EgoUsingCrystal)
							)
						)
						(28 (event claimed: 0))
						(else 
							(if (not (Btst 35))
								(SpeakAudio 560)
								(event claimed: 1)
							)
						)
					)
				)
			)
		)
	)
)

(instance path52 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0004))
					(not (& (OnControl 4 (event x?) (event y?)) $0008))
				)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 557)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance theCastle of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0200))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 558)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setMotion: PolyPath 159 100)
					(event claimed: 1)
				)
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
