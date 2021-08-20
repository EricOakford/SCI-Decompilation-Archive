;;; Sierra Script 1.0 - (do not remove this comment)
(script# CAVE)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	cave 0
)

(local
	dripX = [144 249 283 88 165]
	dripY = [184 221 219 201 168]
	dripIndex
)
(instance cave of Room
	(properties
		picture rBearCave
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW vBear rKoboldCave)
		(Load SOUND sCave)
		(super init:)
		(globalMusic number: sCave play:)
		(bear ignoreActors: init: setPri: 11 stopUpd:)
		(drip init: setScript: dripScript)
		(= dripIndex (Random 0 4))
		(ego
			view: vEgo
			posn: 66 174
			init:
			setScript: entranceMsg
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance bear of Actor
	(properties
		y 144
		x 273
		view vBear
	)
)

(instance drip of Prop
	(properties
		y 204
		x 79
		view rKoboldCave
	)
)

(instance entranceMsg of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 105 138 self)
			)
			(1
				(ego setMotion: MoveTo 166 138 self)
			)
			(2
				(ego view: vEgoStanding)
				(Print 8 0
					#at -1 1
					#width 250
					#mode teJustCenter
					#dispose
					#window blackOut
				)
				(bear setCycle: EndLoop)
				(= seconds 4)
			)
			(3
				(bear loop: 1 cel: 0 cycleSpeed: 1 setCycle: Forward)
				(= seconds 4)
			)
			(4
				(bear setCycle: EndLoop self)
			)
			(5
				(bear loop: 2 cel: 0)
				(= cycles 1)
			)
			(6
				(bear setCycle: EndLoop self)
			)
			(7
				(cls)
				(curRoom newRoom: MEADOW)
			)
		)
	)
)

(instance dripScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(drip
					posn: [dripX dripIndex] [dripY dripIndex]
					setCycle: EndLoop
				)
				(= cycles (Random 20 40))
			)
			(1
				(= dripIndex (Random 0 4))
				(self changeState: 0)
			)
		)
	)
)

(instance blackOut of SysWindow
	(properties
		color vLBLUE
		back vBLACK
	)
)
