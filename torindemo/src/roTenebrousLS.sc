;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50700)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use Motion)
(use Actor)

(public
	roTenebrousLS 0
)

(instance poTorin of Prop
	(properties
		view -14835
	)
)

(instance oCrunch of TPSound
	(properties)
)

(instance soPlayMovie of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theSound lThumbLoop: -14935)
				(poTorin
					loop: 0
					cel: 0
					posn: 184 263
					init:
					setCycle: End self
				)
			)
			(1
				(oCrunch init: lThumbLoop: -14933 self)
			)
			(2
				(theSound lThumbLoop: -14934)
				(poTorin loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(poTorin loop: 2 cel: 0 setCycle: End self)
			)
			(4
				(poTorin loop: 3 cel: 0 posn: 174 257 setCycle: End self)
			)
			(5
				(poTorin dispose:)
				(ego
					posn: 174 257
					init:
					oPanner:
					setLoop: 6 1
					setMotion: MoveTo 194 238 self
				)
			)
			(6
				(ego setPri: 1 setMotion: MoveTo 294 425 self)
			)
			(7 (curRoom newRoom: -14736))
		)
	)
)

(instance roTenebrousLS of TPRoom
	(properties
		picture -14836
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -14836)
		(curRoom setScript: soPlayMovie)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
