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
	local0
	local1
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
	(= i 0)
	(while (< i 4)
		(= [lightning i] (aLightning new:))
		([lightning i]
			setLoop: i
			cel: 0
			ignoreActors:
			minPause: (Random 20 40)
			maxPause: (Random 40 70)
			posn:
				[lightningX i]
				[lightningY i]; (= [lightning i])] (aLightning new:))]
			init:
		)
		(++ i)
	)
)

;EO: This procedure caused the error "Not an object: $b0b".
;It seems to be a decompiler error.
(procedure (AddSound &tmp i)
	(= i 0)
	(while (< i 2)
		;problem code below
;;;		([sound (= [sound i] (Sound new:))]
		;corrected code below
		(= [sound i] (Sound new:))
		([sound i]
			number: 12
			loop: 1
			priority: [soundPri i]
			init:
		)
		(++ i)
	)
)

(procedure (DisposeSound &tmp i)
	(= i 0)
	(while (< i 2)
		([sound i] dispose:)
		(++ i)
	)
)

(procedure (AddBolts &tmp i)
	(= i 0)
	(while (< i 8)
		(= [bolt i] (aBolt new:))
		([bolt i]
			setLoop: (mod i 4)
			cel: 0
			ignoreActors:
			posn: [boltX i] [boltY i]
			init:
		)
		(++ i)
	)
)

(instance aLightning of Extra
	(properties
		view 792
		cycleType 1
		minCycles 1
		maxCycles 2
	)
)

(instance aBolt of Prop
	(properties
		view 792
	)
)

(instance demoIblis of Room
	(properties
		picture 790
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW 791 792)
		(= currentPalette 2)
		(super init:)
		(globalSound number: 340 loop: 1 priority: 8 playBed:)
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
				(= local0 80)
			)
		)
		(if (and (> local0 1) (== (-- local0) 1))
			(= local0 0)
			(curRoom newRoom: SPEED)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if modelessDialog (modelessDialog dispose:))
		(DisposeSound)
		(ego view: 0 setLoop: -1 setCel: -1)
		(= currentPalette 0)
		(super dispose:)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print IBLIS 0 #at -1 160 #dispose)
				(= seconds 5)
			)
			(1
				(Print IBLIS 1 #at -1 160 #mode 1 #dispose)
				(self dispose:)
			)
		)
	)
)

(instance leftBolt of Script
	(properties)
	
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
				(= i 0)
				(while (< i 4)
					([bolt i] setCycle: EndLoop)
					(++ i)
				)
				(= cycles 10)
			)
			(1 (= cycles (Random 5 25)))
			(2 (self changeState: 0))
		)
	)
)

(instance rightBolt of Script
	(properties)
	
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
				(= i 4)
				(while (< i 8)
					([bolt i] setCycle: EndLoop)
					(++ i)
				)
				(= cycles 10)
			)
			(1 (= cycles (Random 5 25)))
			(2 (self changeState: 0))
		)
	)
)

(instance head of PicView
	(properties
		x 157
		y 36
		view 791
	)
)

(instance body of PicView
	(properties
		x 161
		y 80
		view 791
		loop 1
	)
)

(instance smoke of PicView
	(properties
		x 200
		y 119
		view 791
		loop 2
	)
)

(instance rightArm of PicView
	(properties
		x 128
		y 40
		view 791
		loop 3
	)
)

(instance leftArm of PicView
	(properties
		x 197
		y 45
		view 791
		loop 4
	)
)

(instance headFlash of View
	(properties
		x 158
		y 29
		view 791
		cel 1
		priority 2
		signal fixPriOn
	)
)

(instance bodyFlash of View
	(properties
		x 158
		y 45
		view 791
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
		view 791
		loop 3
		cel 1
	)
)

(instance leftHandFlash of View
	(properties
		x 110
		y 105
		view 791
		loop 3
		cel 2
	)
)

(instance rightArmFlash of View
	(properties
		x 218
		y 78
		view 791
		loop 4
		cel 1
	)
)

(instance rightHandFlash of View
	(properties
		x 238
		y 104
		view 791
		loop 4
		cel 2
	)
)
