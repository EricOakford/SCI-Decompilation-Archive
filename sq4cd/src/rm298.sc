;;; Sierra Script 1.0 - (do not remove this comment)
(script# 298)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use Motion)
(use System)

(public
	rm298 0
)

(local
	local0
	eyesTimer
)
(instance rm298 of SQRoom
	(properties
		picture 298
	)
	
	(method (init &tmp [temp0 50])
		(= useSortedFeatures FALSE)
		(HandsOn)
		(Load VIEW 298)
		(Load VIEW 299)
		(self setRegions: BUTTE)
		(super init:)
		(nest init:)
		(theEyes init:)
		(hair init: setCycle: Forward)
		(Roger init:)
		(theView init:)
		(bird init:)
		(self setScript: sRm298)
	)
)

(instance sRm298 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(nest setMotion: MoveTo 130 252 self)
			)
			(2 (curRoom newRoom: 297))
		)
	)
)

(instance nest of Sq4Actor
	(properties
		x -51
		y 177
		yStep 1
		view 299
		moveSpeed 12
		lookStr 2
	)
)

(instance hair of Sq4Prop
	(properties
		x 174
		y 61
		view 298
		priority 6
		signal fixPriOn
		lookStr 1
	)
)

(instance theEyes of Sq4Prop
	(properties
		x 198
		y 83
		view 298
		loop 1
		priority 5
		signal fixPriOn
	)
	
	(method (init)
		(= eyesTimer (Random 10 20))
		(super init: &rest)
		(self doit:)
	)
	
	(method (doit)
		(super doit:)
		(if (== (-- eyesTimer) 0)
			(= eyesTimer (Random 10 20))
			(self setCel: (Random 0 2))
		)
	)
)

(instance bird of Sq4Feature
	(properties
		nsBottom 189
		nsRight 319
		onMeCheck $0080
		lookStr 3
	)
)

(instance Roger of Sq4Feature
	(properties
		noun 7
		nsBottom 189
		nsRight 319
		onMeCheck $0010
		lookStr 4
	)
)

(instance theView of Sq4Feature
	(properties
		x 159
		z -94
		nsBottom 189
		nsRight 319
		sightAngle 90
		lookStr 5
	)
)
