;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64012)
(include sci.sh)
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
	local1
	newEventX
	newEventY
	local4
)
(procedure (localproc_0262 param1 param2 &tmp temp0 temp1 temp2 newEvent temp4 newDText temp6)
	(local1 scaleSignal: 1 y: param1)
	(UpdateScreenItem local1)
	(FrameOut)
	(= temp4
		(CelHigh (local1 view?) (local1 loop?) (local1 cel?))
	)
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(if
			(and
				(<
					(= temp1
						(/
							(* (= temp2 (- screenHeight (newEvent y?))) 100)
							temp4
						)
					)
					253
				)
				(!= temp0 temp1)
			)
			(= temp0 temp1)
			(local1
				scaleX: (Max (/ (* temp0 128) 100) 1)
				scaleY: (Max (/ (* temp0 128) 100) 1)
			)
		)
		(= temp6 (Str format: {%s scale= %d} param2 temp0))
		((= newDText (DText new:))
			text: (Array 8 (temp6 data?))
			fore: 87
			back: 0
			skip: 255
			setSize: 240
			setPri: 255
			posn: newEventX newEventY
			init: (local0 getMainCast:)
		)
		(newEvent localize: local0)
		(local1 x: (newEvent x?))
		(UpdateScreenItem local1)
		(FrameOut)
		(newDText dispose:)
		(temp6 dispose:)
		(newEvent dispose:)
	)
	(newEvent dispose:)
	(return temp0)
)

(procedure (localproc_0442 param1 param2 param3 &tmp newEvent temp1 newDText temp3)
	(= temp1 0)
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(newEvent localize: local0)
		(newEvent y: (Max param1 (newEvent y?)))
		(= temp3 (Str format: {%s y= %d} param2 (newEvent y?)))
		((= newDText (DText new:))
			text: (Array 8 (temp3 data?))
			fore: 87
			back: 0
			skip: 255
			setSize: 240
			setPri: 255
			posn: newEventX newEventY
			init: (local0 getMainCast:)
		)
		(UpdateLine
			param3
			local0
			0
			(newEvent y?)
			(local0 getWidth:)
			(newEvent y?)
			100
			local4
			0
			0
			1
		)
		(FrameOut)
		(newDText dispose:)
		(temp3 dispose:)
		(newEvent dispose:)
	)
	(newEvent localize: local0)
	(= temp1 (Max param1 (newEvent y?)))
	(newEvent dispose:)
	(return temp1)
)

(instance scalerCode of Code
	(properties)
	
	(method (init param1 param2 &tmp newEvent)
		(if (not (RespondsTo param1 341))
			(Printf
				{scalerCode: object %s does not scale}
				(param1 name?)
			)
			(return)
		)
		(= local1 param1)
		(= local0 param2)
		((= newEvent (Event new:)) x: 4)
		(newEvent y: 4)
		(newEvent localize: local0)
		(= newEventX (newEvent x?))
		(= newEventY (newEvent y?))
		(newEvent dispose:)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7)
		(if (not local1)
			(Prints {scalerCode: please init with an object first})
			(return)
		)
		(local1 setScale: 0)
		(= temp0 (local1 x?))
		(= temp1 (local1 y?))
		(= local4 210)
		(= temp6
			(AddLine
				local0
				0
				(local1 y?)
				(local0 getWidth:)
				(local1 y?)
				100
				local4
				0
				0
				1
			)
		)
		(= temp2
			(localproc_0442
				0
				(Str with: {back Y, click when done.___})
				temp6
			)
		)
		(= temp4
			(localproc_0262
				temp2
				(Str with: {Size object, click when done.___})
			)
		)
		(= temp7
			(AddLine
				local0
				0
				(local1 y?)
				(local0 getWidth:)
				(local1 y?)
				100
				local4
				0
				0
				1
			)
		)
		(= temp3
			(localproc_0442
				temp2
				(Str with: {front Y, click when done.___})
				temp7
			)
		)
		(= temp5
			(localproc_0262
				temp3
				(Str with: {Size object, click when done.___})
			)
		)
		(local1 posn: temp0 temp1)
		(Printf {Scaler %d %d %d %d} temp5 temp4 temp3 temp2)
		(local1 setScaler: Scaler temp5 temp4 temp3 temp2)
		(DeleteLine temp6 local0)
		(DeleteLine temp7 local0)
	)
	
	(method (dispose)
		(= local1 0)
	)
)
