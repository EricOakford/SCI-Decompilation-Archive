;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30300)
(include sci.sh)
(use Main)
(use TPRoom)
(use Script)
(use Print)
(use Actor)

(public
	roTorinAndLeenah 0
)

(local
	local0 =  128
	local1 =  1
	local2 =  64
	local3 =  1
)
(instance soRunAnim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {Mushy love scene})
				(ego get: ((ScriptID 64001 1) get: 6))
				(self dispose:)
				(curRoom newRoom: 30400)
			)
		)
	)
)

(instance soZoomAndPan of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (<= (= local0 (- local0 local1)) local2)
					(self cue:)
					(return)
				)
				(= temp0 (+ (voWaterfall x?) local3))
				(MonoOut {newx %d} temp0)
				(if (> temp0 0) (= temp0 0))
				(MonoOut {newx %d} temp0)
				(voWaterfall x: temp0 scaleX: local0 scaleY: local0)
				(if (== temp0 -45) (-- local3))
				(= ticks 8)
				(= state -1)
			)
			(1
				(voWaterfall dispose:)
				(curRoom picture: 30305 drawPic: 30305)
				(self setScript: soRunAnim self)
			)
			(2 (self dispose:))
		)
	)
)

(instance voWaterfall of View
	(properties
		view 30309
	)
)

(instance roTorinAndLeenah of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(= local0 96)
		(= local2 64)
		(= local1 1)
		(= local3 10)
		(voWaterfall
			x: -315
			setScale:
			maxScale: 128
			scaleX: local0
			scaleY: local0
			init:
		)
		(music1 pageSize: 30300)
		(curRoom setScript: soZoomAndPan)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
