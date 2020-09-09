;;; Sierra Script 1.0 - (do not remove this comment)
(script# IBLIS) ;12
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoIblis 0
)

(local
	iblisTimer
	lightningTimer
	[lightning 4]
	[lightningX 4] = [66 111 256 200]
	[lightningY 4] = [35 9 18 -3]
	[sound 6]
	[soundPri 4] = [11 12 13 14]
	[bolt 8]
	[boltX 8] = [104 115 130 91 241 218 253 248]
	[boltY 8] = [111 103 103 112 100 103 108 103]
)
(procedure (AddLightning &tmp i)
	(for ((= i 0)) (< i 4) ((++ i))		
		(= [lightning i] (aLightning new:))
		([lightning i]
			setLoop: i
			cel: 0
			ignoreActors:
			minPause: (Random 20 40)
			maxPause: (Random 40 70)
			posn: [lightningX i] [lightningY i]
			init:
		)
	)
)

(procedure (AddSound &tmp i)
	(for ((= i 0)) (< i 2) ((++ i))	
		(= [sound i] (Sound new:))
		([sound i]
			number: 12
			loop: 1
			priority: [soundPri i]
			init:
		)
	)
)

(procedure (DisposeSound &tmp i)
	(for ((= i 0)) (< i 2) ((++ i))	
		([sound i] dispose:)
	)
)

(procedure (AddBolts &tmp i)
	(for ((= i 0)) (< i 8) ((++ i))	
		(= [bolt i] (aBolt new:))
		([bolt i]
			setLoop: (mod i 4)
			cel: 0
			ignoreActors:
			posn: [boltX i] [boltY i]
			init:
		)
	)
)

(instance aLightning of Extra
	(properties
		view vLightningStrikes
		cycleType ExtraEndLoop
		minCycles 1
		maxCycles 2
	)
)

(instance aBolt of Prop
	(properties
		view vLightningStrikes
	)
)

(instance demoIblis of Room
	(properties
		picture rIblisDestroys
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW vIblisParts vLightningStrikes)
		(= currentPalette 2)
		(super init:)
		(globalSound number: rDestructCity loop: 1 priority: 8 playBed:)
		(addToPics
			add: head body smoke rightArm leftArm
			eachElementDo: #init
			doit:
		)
		(AddLightning)
		(AddBolts)
		(AddSound)
		(headFlash init: hide:)
		(bodyFlash init: hide:)
		(leftArmFlash init: hide:)
		(leftHandFlash init: hide:)
		(rightArmFlash init: hide:)
		(rightHandFlash init: hide:)
		(self setScript: rmScript)
	)
	
	(method (doit)
		(if (mod ([lightning 0] cel?) 2)
			(leftArmFlash show:)
		else
			(leftArmFlash hide:)
		)
		(if (mod ([lightning 1] cel?) 2)
			(bodyFlash show:)
		else
			(bodyFlash hide:)
		)
		(if (mod ([lightning 2] cel?) 2)
			(rightArmFlash show:)
		else
			(rightArmFlash hide:)
		)
		(if (mod ([lightning 3] cel?) 2)
			(headFlash show:)
		else
			(headFlash hide:)
		)
		(if (== (globalSound prevSignal?) 50)
			([bolt 0] setScript: leftBolt)
			([bolt 4] setScript: rightBolt)
			(globalSound prevSignal: 0)
		)
		(if (== (globalSound prevSignal?) -1)
			(globalSound prevSignal: 0)
			(if ([bolt 0] script?)
				(curRoom newRoom: SPEED)
			else
				([bolt 0] setScript: leftBolt)
				([bolt 4] setScript: rightBolt)
				(= iblisTimer 80)
			)
		)
		(if (and (> iblisTimer 1) (== (-- iblisTimer) 1))
			(= iblisTimer 0)
			(curRoom newRoom: SPEED)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(DisposeSound)
		(ego view: 0 setLoop: -1 setCel: -1)
		(= currentPalette 0)
		(super dispose:)
	)
)

(instance rmScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print IBLIS 0
					#at -1 160
					#dispose
				)
				(= seconds 5)
			)
			(1
				(Print IBLIS 1
					#at -1 160
					#mode teJustCenter
					#dispose
				)
				(self dispose:)
			)
		)
	)
)

(instance leftBolt of Script

	(method (doit)
		(if (mod (client cel?) 2)
			(leftHandFlash show:)
			([sound 1] init: play:)
		else
			(leftHandFlash hide:)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(for ((= i 0)) (< i 4) ((++ i))
					([bolt i] setCycle: EndLoop)
				)
				(= cycles 10)
			)
			(1
				(= cycles (Random 5 25))
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance rightBolt of Script
	
	(method (doit)
		(if (mod (client cel?) 2)
			(rightHandFlash show:)
			([sound 0] init: play:)
		else
			(rightHandFlash hide:)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(for ((= i 0)) (< i 4) ((++ i))	
					([bolt i] setCycle: EndLoop)
				)
				(= cycles 10)
			)
			(1
				(= cycles (Random 5 25))
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance head of PicView
	(properties
		x 157
		y 36
		view vIblisParts
	)
)

(instance body of PicView
	(properties
		x 161
		y 80
		view vIblisParts
		loop 1
	)
)

(instance smoke of PicView
	(properties
		x 200
		y 119
		view vIblisParts
		loop 2
	)
)

(instance rightArm of PicView
	(properties
		x 128
		y 40
		view vIblisParts
		loop 3
	)
)

(instance leftArm of PicView
	(properties
		x 197
		y 45
		view vIblisParts
		loop 4
	)
)

(instance headFlash of View
	(properties
		x 158
		y 29
		view vIblisParts
		cel 1
		priority 2
		signal fixPriOn
	)
)

(instance bodyFlash of View
	(properties
		x 158
		y 45
		view vIblisParts
		loop 1
		cel 1
		priority 5
		signal fixPriOn
	)
)

(instance leftArmFlash of View
	(properties
		x 109
		y 78
		view vIblisParts
		loop 3
		cel 1
	)
)

(instance leftHandFlash of View
	(properties
		x 110
		y 105
		view vIblisParts
		loop 3
		cel 2
	)
)

(instance rightArmFlash of View
	(properties
		x 218
		y 78
		view vIblisParts
		loop 4
		cel 1
	)
)

(instance rightHandFlash of View
	(properties
		x 238
		y 104
		view vIblisParts
		loop 4
		cel 2
	)
)
