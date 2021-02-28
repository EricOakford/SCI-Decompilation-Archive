;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include sci.sh)
(use Main)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm305 0
)

(instance rm305 of Rm
	(properties
		noun 5
		picture 305
	)
	
	(method (init)
		(super init:)
		(DontMove)
		(theIconBar disable: 4)
		(kreesha init:)
		(junk init:)
		(apoth init:)
		(laws init:)
		(doneButton init:)
	)
)

(instance doneButton of Prop
	(properties
		x 173
		y 189
		view 930
		loop 15
		cel 1
	)
	
	(method (doVerb)
		(rm305 setScript: sExit)
	)
)

(instance laws of Feature
	(properties
		x 162
		y 90
		nsTop 62
		nsLeft 137
		nsBottom 118
		nsRight 188
	)
	
	(method (doVerb)
		(messager say: 1 6 1)
	)
)

(instance apoth of Feature
	(properties
		x 205
		y 70
		nsTop 60
		nsLeft 196
		nsBottom 81
		nsRight 215
	)
	
	(method (doVerb)
		(messager say: 3 6 3)
	)
)

(instance kreesha of Feature
	(properties
		x 116
		y 71
		nsTop 61
		nsLeft 106
		nsBottom 82
		nsRight 126
	)
	
	(method (doVerb)
		(messager say: 2 6 2)
	)
)

(instance junk of Feature
	(properties
		x 201
		y 118
		nsTop 109
		nsLeft 192
		nsBottom 128
		nsRight 210
	)
	
	(method (doVerb)
		(messager say: 4 6 4)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doneButton setCycle: Beg self)
			)
			(1
				(ego x: 113 y: 126)
				(HandsOn)
				(curRoom newRoom: 300)
			)
		)
	)
)
