;;; Sierra Script 1.0 - (do not remove this comment)
(script# 709)
(include game.sh)
(use Main)
(use Sq4Feature)
(use Motion)
(use Game)
(use System)

(public
	rgLanding 0
	fallScript 1
)

(local
	local0
	local1
	local2
	local3
)
(instance rgLanding of Region
	(properties)
	
	(method (init)
		(super init:)
		(Load VIEW 120)
		(Load PICTURE 120)
		(features
			add: theCityView turbines theRoom
			eachElementDo: #init
			doit:
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((curRoom script?) ((curRoom script?) doit:))
			((IsObjectOnControl ego cGREEN) (HandsOff) (curRoom setScript: fallScript))
		)
	)
	
	(method (newRoom newRoomNumber)
		(= keep (OneOf newRoomNumber 530 535 540))
		(= initialized FALSE)
		(super newRoom: newRoomNumber)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch curRoomNum
					(530
						(= local0 236)
						(= local1 26)
						(= local2 56)
						(= local3 43)
					)
					(535
						(= local0 304)
						(= local1 10)
						(= local2 84)
						(= local3 114)
					)
					(540
						(= local0 242)
						(= local1 55)
						(= local2 58)
						(= local3 213)
					)
				)
				(= cycles 1)
			)
			(1
				(narrator modNum: 709 say: 1)
				(HandsOff)
				(globalSound number: 131 vol: 127 loop: 1 play:)
				(ego
					view: 300
					setLoop:
					setCycle: 0
					setStep: 3 20
					illegalBits: 0
					setMotion: MoveTo (ego x?) 250 self
				)
			)
			(2
				(cast eachElementDo: #hide)
				(curRoom drawPic: 120 PLAIN)
				(ego
					show:
					view: 120
					x:
						(+
							(/
								(* (/ (* (- (ego x?) local1) 100) local0) local2)
								100
							)
							local3
						)
					y: 90
					setLoop: 3
					setCycle: Forward
					setPri: 15
					cel: 0
					setStep: 2 6
					heading: 180
				)
				(= cycles 1)
			)
			(3
				(ego setMotion: MoveTo (ego x?) 210 self)
			)
			(4
				(globalSound fade:)
				(= seconds 1)
			)
			(5 (EgoDead 0 deathFALLHANGAR))
		)
	)
)

(instance theCityView of Sq4Feature
	(properties
		x 153
		y 89
		heading 179
		nsTop 166
		nsBottom 189
		nsRight 319
		sightAngle 90
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 709 say: 2)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		x 160
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch curRoomNum
					(530
						(narrator modNum: 709 say: 3)
					)
					(535
						(narrator modNum: 709 say: 4)
					)
					(540
						(narrator modNum: 709 say: 5)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance turbines of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 180
		onMeCheck $0080
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 709 say: 6)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
