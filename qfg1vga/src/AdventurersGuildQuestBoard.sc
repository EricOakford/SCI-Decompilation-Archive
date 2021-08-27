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
	highlitPoster
	posterLoop
	posterX
	posterY
)
(procedure (PosterPrint theWidth theString)
	(Print
		mode: teJustCenter
		width: theWidth
		addText: theString
		x: -1
		y: 12
		init:
	)
)

(procedure (ChangeHighlight)
	(highlitPoster
		loop: posterLoop
		cel: 0
		posn: posterX posterY
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
		;UPGRADE
;;;		(poster1 init:)
;;;		(poster2 init:)
;;;		(poster3 init:)
;;;		(poster4 init:)
;;;		(poster5 init:)
;;;		(poster6 init:)
		
		(StatusLine enable:)
		(User canControl: FALSE)
		((= highlitPoster (View new:))
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
			(V_DO
				(messager say: N_POSTER V_DO)
			)
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
	
	(method (doVerb theVerb &tmp [str 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster2Script)
			)
			(V_DO
				(messager say: N_POSTER V_DO)
			)
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
	
	(method (doVerb theVerb &tmp [str 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster3Script)
			)
			(V_DO
				(messager say: N_POSTER V_DO)
			)
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
	
	(method (doVerb theVerb &tmp [str 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster4Script)
			)
			(V_DO
				(messager say: N_POSTER V_DO)
			)
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
	
	(method (doVerb theVerb &tmp [str 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster5Script)
			)
			(V_DO
				(messager say: N_POSTER V_DO)
			)
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
	
	(method (doVerb theVerb &tmp [str 400])
		(switch theVerb
			(V_LOOK
				(curRoom setScript: poster6Script)
			)
			(V_DO
				(messager say: N_POSTER V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poster1Script of Script
	(method (changeState newState &tmp [str 400])
		(switch (= state newState)
			(0
				(= posterLoop 0)
				(= posterX 73)
				(= posterY 38)
				(ChangeHighlight)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER NULL NULL 1 @str)
				(PosterPrint 240 @str)
			)
		)
	)
)

(instance poster2Script of Script
	(method (changeState newState &tmp [str 400])
		(switch (= state newState)
			(0
				(= posterLoop 4)
				(= posterX 69)
				(= posterY 112)
				(ChangeHighlight)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER NULL NULL 2 @str)
				(PosterPrint 265 @str)
			)
		)
	)
)

(instance poster3Script of Script
	(method (changeState newState &tmp [str 400])
		(switch (= state newState)
			(0
				(= posterLoop 2)
				(= posterX 126)
				(= posterY 66)
				(ChangeHighlight)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER NULL NULL 3 @str)
				(PosterPrint 265 @str)
			)
		)
	)
)

(instance poster4Script of Script
	(method (changeState newState &tmp [str 400])
		(switch (= state newState)
			(0
				(= posterLoop 1)
				(= posterX 191)
				(= posterY 37)
				(ChangeHighlight)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER NULL NULL 4 @str)
				(PosterPrint 265 @str)
			)
		)
	)
)

(instance poster5Script of Script
	(method (changeState newState &tmp [str 400])
		(switch (= state newState)
			(0
				(= posterLoop 5)
				(= posterX 185)
				(= posterY 99)
				(ChangeHighlight)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER NULL NULL 5 @str)
				(PosterPrint 200 @str)
			)
		)
	)
)

(instance poster6Script of Script
	(method (changeState newState &tmp [str 400])
		(switch (= state newState)
			(0
				(= posterLoop 3)
				(= posterX 253)
				(= posterY 51)
				(ChangeHighlight)
				(= cycles 2)
			)
			(1
				(Message MsgGet 318 N_POSTER NULL NULL 6 @str)
				(Bset fReadBarnardBulletin)
				(PosterPrint 265 @str)
			)
		)
	)
)
