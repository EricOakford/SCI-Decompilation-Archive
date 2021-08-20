;;; Sierra Script 1.0 - (do not remove this comment)
(script# MAGIC)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	magic 0
)

(local
	[thunderingCloud 4]
	theFamiliar
	theLightning
	warpCloud
	zaraBody
)
(instance theThunder of Sound
	(properties
		priority 1
	)
)

(instance theTeleport of Sound
	(properties
		priority 2
	)
)

(instance magic of Room
	(properties
		picture rMagicShop
		style IRISIN
		vanishingY 80
	)
	
	(method (init)
		(LoadMany VIEW rMagicShop vZara)
		(Load SOUND 28 128 45 145 67)
		(super init:)
		(if (== numVoices 1)
			(theThunder number: sThunderIBM)
			(theTeleport number: sTeleportIBM)
		else
			(theThunder number: sThunder)
			(theTeleport number: sTeleport)
		)
		(globalMusic number: sMagicShop loop: 1 play:)
		(ego loop: 3 posn: 159 188 init:)
		((= [thunderingCloud 0] (Prop new:))
			view: rMagicShop
			posn: 159 13
			loop: 0
			cel: 0
			init:
			stopUpd:
		)
		((= [thunderingCloud 1] (Prop new:))
			view: rMagicShop
			posn: 149 27
			loop: 1
			cel: 0
			init:
			stopUpd:
		)
		((= [thunderingCloud 2] (Prop new:))
			view: rMagicShop
			posn: 161 38
			loop: 2
			cel: 0
			init:
			stopUpd:
		)
		((= [thunderingCloud 3] (Prop new:))
			view: rMagicShop
			posn: 160 53
			loop: 3
			cel: 0
			init:
			stopUpd:
		)
		((= theFamiliar (Prop new:))
			view: rMagicShop
			posn: 110 55
			loop: 6
			cel: 0
			init:
			setPri: 10
			stopUpd:
		)
		((View new:)
			view: rMagicShop
			posn: 111 66
			loop: 8
			cel: 0
			setPri: 9
			init:
			stopUpd:
		)
		(theFamiliar setScript: familiarScript)
	)
)

(instance zara of Prop
	(properties
		view vZara
		loop 1
	)
	
	(method (init)
		(super init:)
		(self posn: 159 82 hide:)
		(theThunder init: play:)
		(self setScript: entranceScript)
	)
)

(instance entranceScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				([thunderingCloud 0] setCycle: EndLoop self)
			)
			(1
				([thunderingCloud 1] setCycle: EndLoop self)
			)
			(2
				([thunderingCloud 2] setCycle: EndLoop self)
			)
			(3
				([thunderingCloud 3] setCycle: EndLoop self)
			)
			(4
				((= theLightning (Prop new:))
					view: rMagicShop
					posn: 159 90
					loop: 4
					cel: 0
					init:
					setCycle: EndLoop self
				)
			)
			(5
				(theLightning stopUpd:)
				((= warpCloud (Prop new:))
					view: rMagicShop
					posn: 161 90
					loop: 5
					cel: 0
					init:
					ignoreActors:
					setCycle: CycleTo 3 1 self
				)
				(theTeleport init: play:)
			)
			(6
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(zara loop: 1 cel: 0 show:)
				((= zaraBody (View new:))
					view: 315
					posn: 159 92
					loop: 0
					cel: 0
					init:
					ignoreActors:
					stopUpd:
				)
				(warpCloud setCycle: CycleTo 5 1 self)
			)
			(7
				(zara setCycle: EndLoop self)
			)
			(8
				(warpCloud stopUpd:)
				(= cycles 20)
			)
			(9
				(curRoom newRoom: DART)
				(globalMusic fade:)
			)
		)
	)
)

(instance familiarScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 4 0
					#at -1 285
					#width 290
					#dispose
					#window aTalk
				)
				(theFamiliar cel: 1 posn: 110 54)
				(= seconds 2)
			)
			(1
				(ego setMotion: MoveTo 180 112)
				(theFamiliar
					posn: 110 53
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(zara init:)
				(= cycles 20)
			)
			(3
				(theFamiliar
					loop: 7
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(4
				(theFamiliar setCycle: BegLoop self)
			)
			(6
				(self dispose:)
			)
		)
	)
)

(instance aTalk of SysWindow
	(properties
		color vGREY
		back vLCYAN
	)
)
