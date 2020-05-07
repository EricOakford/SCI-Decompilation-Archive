;;; Sierra Script 1.0 - (do not remove this comment)
(script# CONTROLS)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	controlRm 0
)

(instance controlRm of Room
	(properties
		picture 25
		style IRISOUT
	)
	
	(method (init)
		(LoadMany VIEW 232 133 25 125 225 325 525)
		(ego
			view: 232
			posn: 25 177
			loop: 0
			init:
			setScript: sitControlPanelScript
		)
		(super init:)
		(globalSound number: 11 loop: -1 play:)
		(NormalEgo)
		(addToPics
			add:
				rail1Pic
				rail2Pic
				chairPic
				mapPic
				cornerPic
				mapTable
				scopePic
				mapOnWallPic
				crewMember
				scopePic2
				controlPanelPic
				poleToRailPic
			eachElementDo: #init
			doit:
		)
		(controlPanelChair init:)
		(scopeProp init:)
		(scopeProp2 init:)
		(leftArms init:)
		(rightArms init: isExtra: TRUE)
		(turningProp init: setCycle: Forward isExtra: TRUE)
		(rowOfLights init: setCycle: Forward isExtra: TRUE)
		(scanningProp init: setCycle: Forward isExtra: TRUE)
		(viewerProp init: setCycle: Forward isExtra: TRUE)
		(rotateCap init: setCycle: Forward isExtra: TRUE)
		(redCap init: setCycle: Forward isExtra: TRUE)
		(redCap2 init: setCycle: Forward isExtra: TRUE)
		(rotatingGauge init: setCycle: Forward isExtra: TRUE)
		(rotatingGauge2 init: setCycle: Forward isExtra: TRUE)
		(rotatingGauge3 init: setCycle: Forward isExtra: TRUE)
		(anotherGauge init: setCycle: Forward isExtra: TRUE)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript EXTRA)
	)
)

(instance sitControlPanelScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(Print 25 0 #dispose #at 68 158)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 158 103 self
				)
			)
			(2
				(ego setMotion: MoveTo 187 80 self)
			)
			(3
				(controlPanelChair hide:)
				(ego
					view: 325
					loop: 1
					cel: 0
					posn: 209 80
					setCycle: EndLoop self
				)
			)
			(4 (= cycles 2))
			(5 (curRoom newRoom: 27))
		)
	)
)

(instance mapTable of PicView
	(properties
		y 146
		x 159
		view 25
		signal ignrAct
	)
)

(instance mapOnWallPic of PicView
	(properties
		y 94
		x 270
		z 20
		view 25
		loop 1
		cel 2
		priority 1
		signal ignrAct
	)
)

(instance scopePic2 of PicView
	(properties
		y 112
		x 110
		view 25
		loop 2
		priority 7
	)
)

(instance scopePic of PicView
	(properties
		y 100
		x 144
		view 25
		loop 2
		cel 1
		priority 6
	)
)

(instance scopeProp of Prop
	(properties
		y 112
		x 110
		view 25
		loop 3
		priority 7
		signal ignrAct
	)
)

(instance scopeProp2 of Prop
	(properties
		y 100
		x 145
		view 25
		loop 3
		priority 6
		signal ignrAct
	)
)

(instance mapPic of PicView
	(properties
		y 98
		x 56
		view 25
		loop 1
		priority 0
		signal ignrAct
	)
)

(instance cornerPic of PicView
	(properties
		y 75
		x 130
		view 25
		loop 1
		cel 1
		priority 0
		signal ignrAct
	)
)

(instance chairPic of PicView
	(properties
		y 89
		x 72
		view 25
		cel 1
		priority 5
		signal ignrAct
	)
)

(instance rail1Pic of PicView
	(properties
		y 108
		x 30
		view 25
		cel 2
		priority 7
		signal ignrAct
	)
)

(instance rail2Pic of PicView
	(properties
		y 116
		x 58
		view 25
		cel 3
		priority 8
		signal ignrAct
	)
)

(instance poleToRailPic of PicView
	(properties
		y 108
		x 73
		view 25
		cel 5
		priority 7
	)
)

(instance turningProp of Prop
	(properties
		y 48
		x 84
		view 125
		priority 3
		signal ignrAct
	)
)

(instance rowOfLights of Prop
	(properties
		y 65
		x 54
		view 125
		loop 1
		priority 3
		signal ignrAct
	)
)

(instance scanningProp of Prop
	(properties
		y 74
		x 30
		view 125
		loop 2
		priority 3
		signal ignrAct
	)
)

(instance viewerProp of Prop
	(properties
		y 74
		x 17
		view 125
		loop 3
		priority 3
		signal ignrAct
	)
)

(instance rotateCap of Extra
	(properties
		y 54
		x 45
		view 125
		loop 4
		priority 3
		signal (| ignrAct anExtra)
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance redCap2 of Extra
	(properties
		y 53
		x 55
		view 125
		loop 6
		priority 2
		signal (| ignrAct anExtra)
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance redCap of Extra
	(properties
		y 52
		x 64
		view 125
		loop 5
		priority 3
		signal (| ignrAct anExtra)
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance leftArms of Prop
	(properties
		view 25
		loop 6
	)
	
	(method (init)
		(super init:)
		(self
			setScript: sonarMansArmScript
			setPri: 4
			posn: (- (chairPic x?) 4) (- (chairPic y?) 18)
			ignoreActors: TRUE
		)
	)
)

(instance rightArms of Prop
	(properties
		view 25
		loop 7
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 4
			posn: (- (chairPic x?) 4) (- (chairPic y?) 18)
			ignoreActors: TRUE
		)
	)
	
	(method (addToPic)
		(leftArms
			setScript: 0
			stopUpd:
			signal: (| (leftArms signal?) staticView)
		)
		(super addToPic: &rest)
	)
)

(instance crewMember of PicView
	(properties
		y 76
		x 129
		view 25
		cel 4
		priority 4
	)
)

(instance controlPanelChair of View
	(properties
		y 80
		x 209
		view 125
		loop 8
		priority 5
	)
	
	(method (init)
		(super init: &rest)
		(self view: 125 loop: 8 cel: 0)
	)
)

(instance controlPanelPic of PicView
	(properties
		y 92
		x 203
		view 225
		priority 4
		signal ignrAct
	)
)

(instance sonarMansArmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightArms stopUpd:)
				(leftArms stopUpd:)
				(= seconds (Random 5 10))
			)
			(1
				(rightArms hide:)
				(leftArms show: setCycle: EndLoop self)
			)
			(2
				(leftArms stopUpd:)
				(= seconds (Random 1 4))
			)
			(3
				(leftArms setCycle: BegLoop self)
			)
			(4
				(leftArms stopUpd:)
				(= seconds (Random 5 10))
			)
			(5
				(leftArms hide:)
				(rightArms show: setCycle: EndLoop self)
			)
			(6
				(rightArms stopUpd:)
				(= seconds (Random 1 4))
			)
			(7
				(rightArms setCycle: BegLoop self)
			)
			(8 (self init: client))
		)
	)
)

(instance rotatingGauge of Prop
	(properties
		y 48
		x 74
		view 125
		loop 7
		priority 1
		signal ignrAct
		cycleSpeed 2
	)
)

(instance rotatingGauge2 of Prop
	(properties
		y 58
		x 25
		view 125
		loop 9
		priority 2
		signal ignrAct
	)
)

(instance rotatingGauge3 of Extra
	(properties
		y 60
		x 92
		view 525
		priority 2
		signal (| ignrAct anExtra)
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance anotherGauge of Prop
	(properties
		y 56
		x 125
		view 25
		loop 8
		priority 2
		signal ignrAct
	)
)
