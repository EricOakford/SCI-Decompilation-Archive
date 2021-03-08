;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1025)
(include game.sh) (include "1025.shm")
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1025 0
)

(instance rm1025 of Room
	(properties
		noun N_ROOM
		picture 133
	)
	
	(method (init)
		(LoadMany RES_VIEW 679)
		(= goliathFloor 2)
		(= global135 26)
		(bigarrow init: setCycle: Forward)
		(shieldSwitch init:)
		(light init:)
		(super init:)
		(walkHandler addToFront: self)
		(theGame handsOn:)
		(theIconBar select: (theIconBar at: ICON_DO))
		(theGame setCursor: 982)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb NULL 0)
			)
			(else
				(curRoom newRoom: 1020)
			)
		)
	)
)

(instance sFlipSwitch of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(shieldSwitch setCel: 0)
				(light dispose:)
				(arrow init: setCycle: Forward)
				(SolvePuzzle f1025FlipSwitch 20)
				(= seconds 2)
			)
			(1
				(curRoom newRoom: 1020)
				(self dispose:)
			)
		)
	)
)

(instance arrow of Prop
	(properties
		x 126
		y 116
		noun N_LIGHT
		view 679
		loop 1
		cycleSpeed 8
	)
)

(instance bigarrow of Prop
	(properties
		x 150
		y 73
		noun N_LIGHT
		view 679
	)
)

(instance shieldSwitch of View
	(properties
		x 160
		y 108
		noun N_SWITCH
		view 679
		loop 2
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sFlipSwitch)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance light of View
	(properties
		x 198
		y 117
		noun N_LIGHT
		view 679
		loop 3
	)
)
