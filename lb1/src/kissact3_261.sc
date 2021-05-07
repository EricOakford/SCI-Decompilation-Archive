;;; Sierra Script 1.0 - (do not remove this comment)
(script# 261)
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
	kissact3 0
)

(local
	local0
	local1
)
(instance myMusic of Sound
	(properties)
)

(instance kissact3 of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(if (not fifiSleeping)
			(LoadMany 143 243 287)
			(= [global377 4] 287)
			(LoadMany 132 224 229)
			(LoadMany 128 474 904)
			(= global208 16)
		)
		(Load rsFONT 4)
		(Load rsSCRIPT 985)
		(if
		(and (not (& global118 $0002)) (== [global368 2] 1))
			(LoadMany 135 41)
			(LoadMany 143 406)
			(LoadMany 128 642 472)
			(LoadMany 132 29 94 95 96)
			(Rudy posn: 151 113 loop: 1 init:)
			(Fifi
				view: 460
				loop: 0
				cel: 0
				illegalBits: 0
				posn: 121 113
				init:
			)
		else
			(if (& global118 $0002)
				(= local1 1)
				(Fifi
					view: 472
					loop: 1
					illegalBits: 0
					posn: 216 118
					setPri: 10
					cycleSpeed: 20
					setCycle: Fwd
				)
			else
				(Fifi
					view: 462
					loop: 0
					cel: 0
					illegalBits: 0
					posn: 118 76
				)
				(self setScript: reading)
			)
			(Fifi init:)
		)
	)
	
	(method (doit)
		(if
			(and
				(not (self script?))
				(> (ego x?) 65)
				(not local1)
				(cast contains: Rudy)
			)
			(HandsOff)
			(self setScript: slapHim)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
	)
)

(instance slapHim of Script
	(properties)
	
	(method (doit)
		(if (>= state 1)
			(if (>= state 5) (Face Fifi Rudy))
			(if (and (== state 3) (== (Fifi cel?) 4))
				(myMusic number: 112 loop: 1 priority: 5 play:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0002))
						(= global118 (| global118 $0002))
						(= global173 (| global173 $0008))
						(= [global368 2] 0)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(= local1 (= fifiSleeping 1))
				(Rudy
					view: 387
					cycleSpeed: 2
					cel: 0
					loop: 0
					setCycle: End self
				)
			)
			(2
				(Rudy hide:)
				(Fifi
					view: 467
					ignoreActors: 1
					posn: 114 113
					cel: 0
					loop: 2
					setCycle: Fwd
				)
				(= seconds 3)
			)
			(3
				(Fifi loop: 0 cycleSpeed: 1 setCycle: End self)
				(Print 261 0 #at 90 25 #font 4 #width 125 #draw #dispose)
			)
			(4
				(Rudy
					show:
					loop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
				(Fifi loop: 1 posn: 114 113 cycleSpeed: 1 setCycle: End)
			)
			(5
				(cls)
				(Fifi view: 460 setPri: -1 setCycle: Walk ignoreActors: 0)
				(Rudy loop: 2 cycleSpeed: 0 setCycle: Fwd)
				(Print
					261
					1
					#at
					90
					25
					#font
					(= seconds 4)
					#width
					80
					#draw
					#dispose
				)
			)
			(6
				(cls)
				(Print 261 2 #at 125 25 #font 4 #width 80 #draw #dispose)
				(Rudy
					view: 380
					setCycle: Walk
					setAvoider: (Avoid new:)
					setMotion: MoveTo 44 121 self
				)
			)
			(7
				(cls)
				(Rudy setMotion: MoveTo 44 244 self)
			)
			(8
				(Rudy dispose:)
				(Fifi setScript: lieDown)
				(DisposeScript 985)
				(client setScript: 0)
			)
		)
	)
)

(instance lieDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== fifiSleeping 1)
					(gDoor setScript: playRecord init:)
				)
				(= fifiSleeping 1)
				(= [global377 4] (& [global377 4] $fee0))
				(Fifi
					cycleSpeed: 0
					illegalBits: 0
					setMotion: MoveTo 216 118 self
				)
			)
			(1
				(Fifi
					view: 472
					loop: 0
					cel: 0
					cycleSpeed: 1
					setPri: 10
					ignoreActors: 1
					setCycle: End self
				)
			)
			(2
				(Fifi loop: 1 cycleSpeed: 6 setCycle: Fwd)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance reading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Fifi loop: 0 setCycle: End self)
			)
			(1
				(= state -1)
				(= seconds (Random 8 16))
			)
		)
	)
)

(instance playRecord of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Fwd)
				(mySound
					number: (if (== (mySound number?) 229) 224 else 229)
					loop: 1
					play: self
				)
				(= state -1)
			)
		)
	)
)

(instance mySound of Sound
	(properties)
)

(instance Rudy of Act
	(properties
		y 119
		x 71
		view 380
	)
)

(instance Fifi of Act
	(properties
		y 113
		x 114
		view 467
	)
	
	(method (handleEvent event)
		(if (> (ego x?) 64)
			(= theTalker 5)
			(cond 
				(
				(or (MousedOn self event 3) (Said 'examine/fifi'))
					(cond 
						((not (& global207 $0010))
							(= theTalker 5)
							(= global207 (| global207 $0010))
							(Say 0 261 3)
						)
						(fifiSleeping (Print 261 4))
						(else (Print 261 5))
					)
					(event claimed: 1)
				)
				((and fifiSleeping (Said 'hear/fifi')) (Print 261 6))
				(
					(and
						fifiSleeping
						(or
							(Said 'deliver,hold/*<fifi')
							(Said 'deliver,hold/*/fifi')
						)
					)
					(if (and theInvItem haveInvItem)
						(Print 261 6)
					else
						(DontHave)
					)
				)
				((and fifiSleeping (Said 'examine[<at]/bed')) (Print 261 4))
				((and fifiSleeping (Said 'awaken/fifi')) (Print 261 7))
				((and fifiSleeping (Said 'ask,tell//*<about'))
					(switch local0
						(0 (Print 261 7))
						(else  (Print 261 6))
					)
					(++ local0)
				)
				((Said 'converse/fifi')
					(if fifiSleeping
						(switch local0
							(0 (Print 261 7))
							(else  (Print 261 6))
						)
					else
						(switch local0
							(0 (Say 1 261 8))
							(1 (Say 1 261 9))
							(2 (Say 1 261 10))
							(else  (Print 261 11))
						)
					)
					(++ local0)
				)
				((Said '/fifi>')
					(cond 
						((Said 'get') (Print 261 12))
						((Said 'kill') (Print 261 13))
						((Said 'kiss') (Print 261 14))
						((Said 'embrace') (Print 261 15))
					)
				)
			)
		)
	)
)
