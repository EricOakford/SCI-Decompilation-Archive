;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use DText)
(use String)
(use Print)
(use Scaler)
(use System)

(public
	scalerCode 0
)

(local
	local0
)
(procedure (localproc_014d param1 param2 &tmp temp0 temp1 temp2 newEvent temp4 newDText temp6)
	(ego scaleSignal: 1 y: param1)
	(UpdateScreenItem ego)
	(FrameOut)
	(= temp4 (CelHigh (ego view?) (ego loop?) (ego cel?)))
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(if
			(and
				(<
					(= temp1
						(/ (* (= temp2 (- 200 (newEvent y?))) 100) temp4)
					)
					253
				)
				(!= temp0 temp1)
			)
			(= temp0 temp1)
			(ego
				scaleX: (Max (/ (* temp0 128) 100) 1)
				scaleY: (Max (/ (* temp0 128) 100) 1)
			)
		)
		(= temp6 (String format: {%s scale= %d} param2 temp0))
		((= newDText (DText new:))
			text: (String StrDup (temp6 data?))
			fore: 255
			back: 0
			setSize: 240
			setPri: 255
			init:
		)
		(ego x: (newEvent x?))
		(UpdateScreenItem ego)
		(FrameOut)
		(newDText dispose:)
		(temp6 dispose:)
		(newEvent dispose:)
	)
	(newEvent dispose:)
	(return temp0)
)

(procedure (localproc_0295 param1 param2 param3 &tmp newEvent temp1 newDText temp3)
	(= temp1 0)
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(newEvent y: (Max (+ param1 1) (- (newEvent y?) 10)))
		(= temp3 (String format: {%s y= %d} param2 (newEvent y?)))
		((= newDText (DText new:))
			text: (String StrDup (temp3 data?))
			fore: 255
			back: 0
			setSize: 240
			setPri: 255
			init:
		)
		(UpdateLine
			param3
			(cast plane?)
			0
			(newEvent y?)
			320
			(newEvent y?)
			100
			local0
			0
			0
			1
		)
		(FrameOut)
		(newDText dispose:)
		(temp3 dispose:)
		(newEvent dispose:)
	)
	(= temp1 (Max (+ param1 1) (- (newEvent y?) 10)))
	(newEvent dispose:)
	(return temp1)
)

(instance scalerCode of Code
	(properties)
	
	(method (doit &tmp egoX egoY temp2 temp3 temp4 temp5 temp6 temp7)
		(ego setScale: 0)
		(= egoX (ego x?))
		(= egoY (ego y?))
		(= local0 (Palette PalMatch 255 0 0))
		(= temp6
			(AddLine
				(cast plane?)
				0
				(ego y?)
				320
				(ego y?)
				100
				local0
				0
				0
				1
			)
		)
		(= temp2
			(localproc_0295
				-1
				(String with: {back Y, click when done.___})
				temp6
			)
		)
		(= temp4
			(localproc_014d
				temp2
				(String with: {Size object, click when done.___})
			)
		)
		(= temp7
			(AddLine
				(cast plane?)
				0
				(ego y?)
				320
				(ego y?)
				100
				local0
				0
				0
				1
			)
		)
		(= temp3
			(localproc_0295
				temp2
				(String with: {front Y, click when done.___})
				temp7
			)
		)
		(= temp5
			(localproc_014d
				temp3
				(String with: {Size object, click when done.___})
			)
		)
		(ego posn: egoX egoY)
		(Printf {Scaler %d %d %d %d} temp5 temp4 temp3 temp2)
		(ego setScaler: Scaler temp5 temp4 temp3 temp2)
		(DeleteLine temp6 (cast plane?))
		(DeleteLine temp7 (cast plane?))
	)
)
