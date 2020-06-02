;;; Sierra Script 1.0 - (do not remove this comment)
(script# 318)
(include game.sh) (include "318.shm")
(use Main)
(use Print)
(use Feature)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm318 0
)

(local
	newView
	local1
	local2
	local3
)
(procedure (localproc_0060 param1 param2)
	(Print
		mode: teJustCenter
		width: param1
		addText: param2
		x: -1
		y: 12
		init:
	)
)

(procedure (localproc_007f)
	(newView
		loop: local1
		cel: 0
		posn: local2 local3
		setPri: 1
	)
)

(instance rm318 of Room
	(properties
		picture 318
		style IRISOUT
	)
	
	(method (init)
		(Load RES_VIEW 318)
		(Load RES_PIC 318)
		(theIconBar disable: ICON_ACTIONS ICON_USEIT ICON_INVENTORY)
		(= picture 318)
		(super init: &rest)
		(features
			add: poster1 poster2 poster3 poster4 poster5 poster6
			eachElementDo: #init
		)
		(StatusLine enable:)
		(User canControl: FALSE)
		((= newView (View new:))
			view: 318
			loop: 0
			cel: 0
			posn: 74 38
			setPri: 1
			init:
			ignoreActors:
			stopUpd:
		)
	)
	
	(method (dispose)
		(User canControl: TRUE)
		(theIconBar enable: ICON_ACTIONS ICON_USEIT ICON_INVENTORY)
		(super dispose:)
	)
	
	(method (doVerb theVerb param2)
		(if (OneOf theVerb V_DO V_LOOK V_WALK)
			(curRoom newRoom: 311)
		else
			(super doVerb: theVerb param2 &rest)
		)
	)
)

(instance poster1 of Feature
	(properties
		x 65
		y 64
		nsTop 38
		nsLeft 42
		nsBottom 90
		nsRight 88
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster1Script)
			)
			(V_DO (messager say: N_POSTER V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poster2 of Feature
	(properties
		x 63
		y 130
		nsTop 115
		nsLeft 35
		nsBottom 145
		nsRight 92
		sightAngle 40
	)
	
	(method (doVerb theVerb &tmp [temp0 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster2Script)
			)
			(V_DO (messager say: N_POSTER V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poster3 of Feature
	(properties
		x 123
		y 94
		nsTop 67
		nsLeft 99
		nsBottom 121
		nsRight 147
		sightAngle 40
	)
	
	(method (doVerb theVerb &tmp [temp0 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster3Script)
			)
			(V_DO (messager say: N_POSTER V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poster4 of Feature
	(properties
		x 189
		y 60
		nsTop 39
		nsLeft 168
		nsBottom 82
		nsRight 210
		sightAngle 40
	)
	
	(method (doVerb theVerb &tmp [temp0 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster4Script)
			)
			(V_DO (messager say: N_POSTER V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poster5 of Feature
	(properties
		x 186
		y 125
		nsTop 101
		nsLeft 155
		nsBottom 150
		nsRight 218
		sightAngle 40
	)
	
	(method (doVerb theVerb &tmp [temp0 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster5Script)
			)
			(V_DO (messager say: N_POSTER V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poster6 of Feature
	(properties
		x 254
		y 90
		nsTop 53
		nsLeft 228
		nsBottom 128
		nsRight 281
		sightAngle 40
	)
	
	(method (doVerb theVerb &tmp [temp0 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster6Script)
			)
			(V_DO (messager say: N_POSTER V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poster1Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 400])
		(switch (= state newState)
			(0
				(= local1 0)
				(= local2 73)
				(= local3 38)
				(localproc_007f)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER 0 0 1 @temp0)
				(localproc_0060 240 @temp0)
			)
		)
	)
)

(instance poster2Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 400])
		(switch (= state newState)
			(0
				(= local1 4)
				(= local2 69)
				(= local3 112)
				(localproc_007f)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER 0 0 2 @temp0)
				(localproc_0060 265 @temp0)
			)
		)
	)
)

(instance poster3Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 400])
		(switch (= state newState)
			(0
				(= local1 2)
				(= local2 126)
				(= local3 66)
				(localproc_007f)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER 0 0 3 @temp0)
				(localproc_0060 265 @temp0)
			)
		)
	)
)

(instance poster4Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 400])
		(switch (= state newState)
			(0
				(= local1 1)
				(= local2 191)
				(= local3 37)
				(localproc_007f)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER 0 0 4 @temp0)
				(localproc_0060 265 @temp0)
			)
		)
	)
)

(instance poster5Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 400])
		(switch (= state newState)
			(0
				(= local1 5)
				(= local2 185)
				(= local3 99)
				(localproc_007f)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER 0 0 5 @temp0)
				(localproc_0060 200 @temp0)
			)
		)
	)
)

(instance poster6Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 400])
		(switch (= state newState)
			(0
				(= local1 3)
				(= local2 253)
				(= local3 51)
				(localproc_007f)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER 0 0 6 @temp0)
				(Bset fReadBarnardBulletin)
				(localproc_0060 265 @temp0)
			)
		)
	)
)
