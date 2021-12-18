;;; Sierra Script 1.0 - (do not remove this comment)
(script# 914)
(include sci.sh)
(use Main)
(use Procs)
(use Plane)
(use Game)
(use Actor)
(use System)

(public
	deadRoom 0
)

(local
	local0
	local1
)
(instance buttonCast of Cast
	(properties)
)

(instance deadRoom of Room
	(properties)
	
	(method (init)
		(theGame handsOn:)
		(= local0
			((Plane new:)
				picture: 914
				priority: 40
				init: 0 0 320 200
				addCast: buttonCast
				yourself:
			)
		)
		(buttonCast plane: local0)
		(sounds stopAll:)
		(aOkButton init:)
		(super init:)
		(switch (Random 0 18)
			(0 (= local1 -30465))
			(1 (= local1 -30462))
			(2 (= local1 -30461))
			(3 (= local1 -30460))
			(4 (= local1 -30459))
			(5 (= local1 -30458))
			(6 (= local1 -30457))
			(7 (= local1 -30454))
			(8 (= local1 -30452))
			(9 (= local1 -30451))
			(10 (= local1 -30450))
			(11 (= local1 -30449))
			(12 (= local1 -30448))
			(13 (= local1 -30447))
			(14 (= local1 -30446))
			(15 (= local1 -30444))
			(16 (= local1 -30443))
			(17 (= local1 -30442))
			(18 (= local1 -30463))
		)
		(proc951_7 local1)
		(proc951_9 local1)
		(sounds play: local1 0 75 0)
	)
	
	(method (dispose)
		(sounds stop: local1)
		(local0 dispose:)
		(super dispose:)
	)
)

(instance aOkButton of Prop
	(properties
		x 150
		y 97
		view 914
	)
	
	(method (init)
		(super init: buttonCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(buttonCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: local0)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
			)
			(event claimed: 1)
			(curRoom setScript: sOk)
		)
	)
)

(instance sOk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sounds play: 15037 0 106 0)
				(aOkButton cel: 1)
				(UpdateScreenItem aOkButton)
				(= cycles 1)
			)
			(1
				(aOkButton cel: 0)
				(UpdateScreenItem aOkButton)
				(if global108
					(= global105 0)
					(global108 dispose:)
					(= global108 0)
				)
				((ScriptID 950 0) dispose:)
				(theGame handsOn:)
				(curRoom newRoom: 910)
				(self dispose:)
			)
		)
	)
)
