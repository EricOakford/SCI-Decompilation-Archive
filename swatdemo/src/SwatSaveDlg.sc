;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWATSAVE)
(include game.sh)
(use DButton)
(use DIcon)
(use Save)
(use System)


(class SwatSaveDlg of SRDialog
	
	(method (doit &tmp temp0)
		(= temp0 (super doit: &rest))
		(DisposeScript SWATSAVE temp0)
	)
	
	(method (dispose)
		(saveView dispose:)
		(super dispose: &rest)
	)
	
	(method (initStrs)
	)
	
	(method (initItems &tmp temp0)
		(selectorObj width: 118 length: 11 setSize:)
		(self moveItems: 2)
		(self moveItems: 3)
		(self add: saveView)
		(super initItems:)
	)
	
	(method (setupObjs)
		(super setupObjs:)
		(= textObj (= changeDirObj 0))
		(selectorObj upButton: selectUp downButton: selectDown)
		(okObj view: 304 loop: 1)
		(deleteObj view: 304 loop: 2)
		(cancelObj view: 304 loop: 3)
	)
	
	(method (setupOkButn param1)
		(switch param1
			(1 (okObj loop: 1))
			(else  (okObj loop: 8))
		)
	)
	
	(method (getDefaultItem param1)
		(switch param1
			(1 editObj)
			(else  okObj)
		)
	)
	
	(method (displayReplaceMsg)
		(return 1)
	)
	
	(method (displayDeleteMsg)
		(return 1)
	)
	
	(method (moveItems param1)
		(if (and editObj (OneOf param1 0 1))
			(editObj moveTo: 18 24)
		)
		(if (and selectorObj (OneOf param1 0 2))
			(selectorObj moveTo: 18 47)
		)
		(if (and okObj (OneOf param1 0 3))
			(okObj moveTo: 158 (selectorObj nsTop?))
		)
		(if (and deleteObj (OneOf param1 0 5))
			(deleteObj
				moveTo: 158 (+ (okObj nsBottom?) buttonMargin)
			)
		)
		(if (and cancelObj (OneOf param1 0 4))
			(cancelObj
				moveTo:
					158
					(if changeDirObj
						(+ (changeDirObj nsBottom?) buttonMargin)
					else
						(+ (deleteObj nsBottom?) buttonMargin)
					)
			)
		)
	)
)

(instance saveView of DIcon
	(properties
		view 304
	)
)

(instance selectUp of DButton
	(properties
		x 141
		y 46
		view 304
		loop 4
	)
	
	(method (moveTo)
		(super moveTo: 141 45)
	)
)

(instance selectDown of DButton
	(properties
		x 141
		y 110
		view 304
		loop 5
	)
	
	(method (moveTo)
		(super moveTo: 141 110)
	)
)
