;;; Sierra Script 1.0 - (do not remove this comment)
(script# 264)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	lillGun 0
)
(synonyms
	(lil person girl)
)

(local
	local0
	local1
	local2
)
(procedure (localproc_000c)
	(Print
		&rest
		#at
		40
		10
		#font
		4
		#width
		125
		#mode
		1
		#draw
		#dispose
	)
)

(instance lillGun of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(if (not (& global118 $0002))
			(LoadMany 135 4 41)
			(LoadMany 132 29 94 95 96)
			(Load rsSCRIPT 406)
		)
		(Load rsVIEW 642 904)
		(LoadMany 143 243 295)
		(= global208 32)
		(= [global377 5] 295)
		(Lillian setCycle: Walk init:)
		(self setScript: reading)
	)
	
	(method (doit)
		(if (Lillian inRect: 235 102 267 118)
			(Lillian setPri: 8)
		else
			(Lillian setPri: -1)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(cond 
				((Said 'converse/lil')
					(= theTalker 6)
					(switch local1
						(0 (Say 1 264 0) (Say 1 264 1))
						(1 (Say 1 264 2))
						(2
							(Say 1 264 3)
							(Say 1 264 4)
							(Say 1 264 5)
						)
						(3 (Say 1 264 6))
						(4 (Say 1 264 7))
						(5 (Say 1 264 8))
						(else  (Say 1 264 9))
					)
					(++ local1)
				)
				((Said 'hear/lil') (Print 264 10))
			)
		)
	)
)

(instance reading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== currentAct 3)
					(cond 
						((not global216) (= state -1))
						((not (& global118 $0002))
							(= global118 (| global118 $0002))
							(self setScript: (ScriptID 406 0))
							(= state -1)
						)
						((self script?) (= state -1))
					)
				)
				(= cycles 1)
			)
			(1
				(localproc_000c 264 11)
				(= seconds 7)
			)
			(2
				(cls)
				(Face Lillian ego)
				(gDoor setCycle: Beg)
				(= theTalker 6)
				(Say 1 264 12)
				(= seconds 3)
			)
			(3
				(Lillian
					setAvoider: (Avoid new:)
					setMotion: MoveTo 262 120 self
				)
			)
			(4
				(lHead setPri: 8 setScript: headActions init:)
				(Lillian
					view: 516
					illegalBits: 0
					posn: 260 118
					loop: 2
					cel: 0
					setAvoider: 0
					setScript: lilActions
				)
				(ego observeControl: 16384)
				(DisposeScript 985)
				(client setScript: 0)
			)
		)
	)
)

(instance headActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (not local2)
					(lHead
						loop: (if (< (Random 1 100) 51) 1 else 2)
						setCycle: End
					)
				)
				(= seconds (Random 6 12))
			)
			(2
				(lHead setCycle: Beg)
				(= state 0)
				(= seconds (Random 6 12))
			)
		)
	)
)

(instance lilActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(Lillian loop: 0 cycleSpeed: 1 setCycle: End)
				(if (< (Random 1 100) 50) (= state 5))
				(= seconds (Random 6 12))
			)
			(1
				(Lillian loop: 5 setCycle: Fwd)
				(= seconds (Random 5 8))
			)
			(2
				(Lillian
					loop: 0
					cel: (- (NumCels Lillian) 1)
					setCycle: Beg
				)
				(= local2 1)
				(if (!= (lHead cel?) 0) (lHead setCycle: Beg))
				(= seconds (Random 5 8))
			)
			(3
				(Lillian loop: 3 cel: 0 setCycle: End self)
			)
			(4
				(Lillian loop: 4 setCycle: Fwd)
				(= seconds 2)
			)
			(5
				(Lillian
					loop: 3
					cel: (- (NumCels Lillian) 1)
					setCycle: Beg
				)
				(= seconds (Random 5 8))
			)
			(6
				(= local2 0)
				(Lillian loop: 6 cel: 0 setCycle: End)
				(= seconds (Random 5 8))
			)
			(7
				(Lillian setCycle: Beg)
				(= seconds (Random 5 8))
			)
			(8
				(= state -1)
				(= seconds (Random 5 8))
			)
		)
	)
)

(instance goSee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theTalker 6)
				(Say 1 264 13)
				(if
					(or
						(ego inRect: 64 115 107 131)
						(ego inRect: 250 115 275 127)
					)
					(ego setMotion: MoveTo (ego x?) (+ (ego y?) 20) self)
				else
					(= cycles 1)
				)
			)
			(1
				(Face ego Lillian)
				(lHead hide:)
				(Lillian
					view: 500
					loop: 2
					setCycle: Walk
					cycleSpeed: 0
					setAvoider: ((Avoid new:) offScreenOK: 1)
					setMotion: MoveTo 94 123 self
				)
			)
			(2
				(gMySound setCycle: End)
				(gBdoor setCycle: End self)
				(doorFX number: 43 loop: 1 play:)
			)
			(3
				(Lillian setMotion: MoveTo 44 123 self)
			)
			(4
				(gMySound setCycle: Beg)
				(gBdoor setCycle: Beg self)
				(doorFX number: 44 loop: 1 play:)
			)
			(5
				(Print 264 14 #dispose)
				(= seconds 3)
			)
			(6
				(cls)
				(gMySound setCycle: End)
				(gBdoor setCycle: End self)
				(doorFX number: 43 loop: 1 play:)
			)
			(7
				(Lillian setMotion: MoveTo 94 123 self)
			)
			(8
				(gMySound setCycle: Beg)
				(gBdoor setCycle: Beg self)
				(doorFX number: 44 loop: 1 play:)
				(= theTalker 6)
				(Say 1 264 15)
			)
			(9
				(gMySound stopUpd:)
				(gBdoor stopUpd:)
				(Lillian setMotion: MoveTo 262 120 self)
			)
			(10
				(lHead show: setPri: 8 setScript: headActions)
				(Lillian
					view: 516
					illegalBits: 0
					posn: 260 118
					loop: 2
					cel: 0
					setAvoider: 0
					setScript: lilActions
				)
				(DisposeScript 985)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance Lillian of Act
	(properties
		y 95
		x 128
		view 500
		loop 3
	)
	
	(method (handleEvent event)
		(= theTalker 6)
		(cond 
			(
			(and (Btst 51) (Said 'tell[/lil]/gertie<about'))
				(if (& global123 $0001)
					(if (& global145 $0001)
						(Say 1 264 16)
						(event claimed: 1)
					else
						(= global145 (| global145 $0001))
						(self setScript: goSee)
						(event claimed: 1)
					)
				else
					(event claimed: 0)
				)
			)
			(
			(and (Btst 51) (Said 'tell[/lil]/actress<about>'))
				(if (& global123 $0004)
					(if (& global145 $0001)
						(Say 1 264 16)
						(event claimed: 1)
					else
						(= global145 (| global145 $0001))
						(self setScript: goSee)
						(event claimed: 1)
					)
				else
					(event claimed: 0)
				)
			)
			((and (Btst 51) (Said 'tell[/lil]/c<about'))
				(if (& global123 $0002)
					(if (& global145 $0001)
						(Say 1 264 16)
						(event claimed: 1)
					else
						(= global145 (| global145 $0001))
						(self setScript: goSee)
					)
				else
					(event claimed: 0)
				)
			)
			(
			(and (MousedOn self event 3) (not (& global207 $0020))) (event claimed: 1) (ParseName {lillian}))
			(
				(and
					(& global207 $0020)
					(or (MousedOn self event 3) (Said 'examine/lil'))
				)
				(event claimed: 1)
				(if local0 (Print 264 17) else (Print 264 18))
			)
		)
	)
)

(instance lHead of Prop
	(properties
		y 85
		x 264
		view 516
		loop 1
	)
)

(instance doorFX of Sound
	(properties)
)
