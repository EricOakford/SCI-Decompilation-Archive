;;; Sierra Script 1.0 - (do not remove this comment)
(script# 295)
(include sci.sh)
(use Main)
(use LBRoom)
(use Talker)
(use LoadMany)
(use Actor)
(use System)

(public
	rm295 0
	myORiley 19
)

(instance rm295 of LBRoom
	(properties
		picture 295
		south 290
	)
	
	(method (init)
		(LoadMany 128 295 1295)
		(LoadMany 132 295)
		(super init:)
		(theMusic number: 295 flags: 1 loop: -1 play:)
		(curRoom setScript: sInsideRoom)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sInsideRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(messager say: 13 0 0 0 self)
			)
			(2 (= cycles 6))
			(3
				(curRoom drawPic: 780)
				(folderInset init:)
				(= cycles 1)
			)
			(4
				(narrator x: 100 y: 100)
				(messager say: 15 0 0 0 self)
			)
			(5 (= cycles 6))
			(6
				(folderInset dispose:)
				(Bset 43)
				(curRoom drawPic: 295)
				(= cycles 1)
			)
			(7
				(messager say: 14 0 0 0 self)
			)
			(8
				(curRoom newRoom: 290)
				(self dispose:)
			)
		)
	)
)

(instance myORiley of Talker
	(properties
		x 0
		y 0
		view 1295
		loop 3
		disposeWhenDone 0
		talkWidth 280
		back 15
		textX 10
		textY 140
	)
	
	(method (init)
		(= font userFont)
		(super init: oRileyBust 0 oRileyMouth &rest)
	)
)

(instance oRileyBust of Prop
	(properties
		view 1295
		loop 1
	)
)

(instance oRileyMouth of Prop
	(properties
		nsTop 36
		nsLeft 254
		view 1295
	)
)

(instance folderInset of View
	(properties
		x 5
		y 5
		view 295
	)
)
