;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64917)
(include sci.sh)
(use Main)
(use QGSet)
(use Styler)
(use DText)
(use Array)
(use Actpr)
(use System)


(class Plane of Obj
	(properties
		scratch 0
		resX 320
		resY 200
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -1
		style $0000
		priority -1
		back 0
		bitmap 0
		casts 0
		mirrored 0
	)
	
	(method (init param1 param2 param3 param4 param5 param6 param7 param8)
		(planes add: self)
		(if argc
			(self setRect: param1 param2 param3 param4)
			(if (> argc 4)
				(self setInsetRect: param5 param6 param7 param8)
			)
		)
		(AddPlane self)
		(= casts (QGSet new:))
	)
	
	(method (dispose)
		(casts delete: cast dispose:)
		(= casts (= bitmap 0))
		(planes delete: self)
		(DeletePlane self)
		(super dispose:)
	)
	
	(method (addCast param1)
		(casts add: (param1 add: plane: self yourself:))
	)
	
	(method (deleteCast param1)
		(casts delete: (param1 plane: 0 yourself:))
	)
	
	(method (drawPic pic theStyle)
		(= picture pic)
		(if (> argc 1) (= style theStyle))
		(= mirrored (if (& style $0400) 1 else 0))
		(UpdatePlane self)
		(Styler doit: self style)
	)
	
	(method (findCast param1 &tmp castsNextNode temp1)
		(= castsNextNode (FirstNode (casts elements?)))
		(while castsNextNode
			(casts nextNode: (NextNode castsNextNode))
			(= temp1 (NodeValue castsNextNode))
			(if (not (String 7 (temp1 name?) param1))
				(return temp1)
			)
			(= castsNextNode (casts nextNode?))
		)
		(return 0)
	)
	
	(method (onMe theObjOrX theY &tmp theObjOrXX theObjOrXY)
		(if (== argc 1)
			(= theObjOrXX (theObjOrX x?))
			(= theObjOrXY (theObjOrX y?))
		else
			(= theObjOrXX theObjOrX)
			(= theObjOrXY theY)
		)
		(return
			(if
				(and
					(<= left theObjOrXX)
					(<= theObjOrXX right)
					(<= top theObjOrXY)
				)
				(<= theObjOrXY bottom)
			else
				0
			)
		)
	)
	
	(method (posn param1 param2 param3 &tmp temp0 temp1 curRoomPlaneLeft curRoomPlaneTop curRoomPlaneRight curRoomPlaneBottom curRoomPlane temp7)
		(cond 
			((> argc 2)
				(= curRoomPlaneLeft (param3 left:))
				(= curRoomPlaneTop (param3 top?))
				(= curRoomPlaneRight (param3 right:))
				(= curRoomPlaneBottom (param3 bottom?))
			)
			(
			(and curRoom (= curRoomPlane (curRoom plane?)))
				(= curRoomPlaneLeft (curRoomPlane left:))
				(= curRoomPlaneTop (curRoomPlane top?))
				(= curRoomPlaneRight (curRoomPlane right:))
				(= curRoomPlaneBottom (curRoomPlane bottom?))
			)
			(else
				(= curRoomPlaneLeft 0)
				(= curRoomPlaneTop 0)
				(= curRoomPlaneRight 319)
				(= curRoomPlaneBottom 199)
			)
		)
		(= temp0 (- right left))
		(= temp1 (- bottom top))
		(if (== param1 -1)
			(= param1
				(+
					curRoomPlaneLeft
					(/ (- (- curRoomPlaneRight curRoomPlaneLeft) temp0) 2)
				)
			)
		else
			(= param1 (+ param1 curRoomPlaneLeft))
		)
		(if (== param2 -1)
			(= param2
				(+
					curRoomPlaneTop
					(/ (- (- curRoomPlaneBottom curRoomPlaneTop) temp1) 2)
				)
			)
		else
			(= param2 (+ param2 curRoomPlaneTop))
		)
		(self
			setRect: param1 param2 (+ param1 temp0) (+ param2 temp1)
		)
	)
	
	(method (scaleBitmap theTheLeft theTheTop theTheRight theTheBottom &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 theLeft theTop theRight theBottom)
		(if argc
			(= theLeft theTheLeft)
			(= theTop theTheTop)
			(= theRight theTheRight)
			(= theBottom theTheBottom)
		else
			(= theLeft left)
			(= theTop top)
			(= theRight right)
			(= theBottom bottom)
		)
		(= temp1
			(CelWide (bitmap view?) (bitmap loop?) (bitmap cel?))
		)
		(= temp3
			(CelHigh (bitmap view?) (bitmap loop?) (bitmap cel?))
		)
		(= temp0 (+ (- theRight theLeft) 1))
		(= temp2 (+ (- theBottom theTop) 1))
		(= temp4 (/ (* temp0 128) temp1))
		(= temp5 (/ (* temp2 128) temp3))
		(if (< temp0 250)
			(= temp4 (/ (* temp0 128) temp1))
		else
			(= temp4 (* (/ (* (/ temp0 2) 128) temp1) 2))
		)
		(bitmap scaleSignal: 1 scaleX: temp4 scaleY: temp5)
		(UpdateScreenItem bitmap)
	)
	
	(method (setBitmap param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
		(= temp3 0)
		(if bitmap
			(bitmap dispose:)
			(= temp0 (self findCast: {planeBM}))
		else
			(= temp0 (Cast new: name: {planeBM}))
			(self addCast: temp0)
		)
		(if (< argc 3)
			((= bitmap (param1 new:)) posn: 0 0 init: temp0)
			(if (> argc 1) (= temp3 param2))
		else
			((= bitmap (View new:))
				view: param1
				loop: param2
				cel: param3
				posn: 0 0
				init: temp0
			)
			(if (> argc 3) (= temp3 param4))
		)
		(= temp1
			(CelWide (bitmap view?) (bitmap loop?) (bitmap cel?))
		)
		(= temp2
			(CelHigh (bitmap view?) (bitmap loop?) (bitmap cel?))
		)
		(if temp3
			(self scaleBitmap:)
		else
			(self setRect: left top (+ left temp1) (+ top temp2))
		)
	)
	
	(method (setInsetRect theInLeft theInTop theInRight theInBottom)
		(= inLeft theInLeft)
		(= inTop theInTop)
		(= inRight theInRight)
		(= inBottom theInBottom)
	)
	
	(method (setRect theLeft theTop theRight theBottom param5)
		(= left theLeft)
		(= top theTop)
		(= right theRight)
		(self
			setInsetRect: theLeft theTop theRight (= bottom theBottom)
		)
		(if (and bitmap (& (bitmap scaleSignal?) $0001))
			(self scaleBitmap:)
		)
		(if (and (> argc 4) param5) (UpdatePlane self))
	)
	
	(method (setSize &tmp temp0 temp1 castsNextNode temp3 temp4 temp5)
		(= temp0 (= temp1 0))
		(= castsNextNode (FirstNode (casts elements?)))
		(while castsNextNode
			(casts nextNode: (NextNode castsNextNode))
			(= temp4 (NodeValue castsNextNode))
			(= temp3 (FirstNode (temp4 elements?)))
			(while temp3
				(temp4 nextNode: (NextNode temp3))
				(if
				(> ((= temp5 (NodeValue temp3)) nsRight?) temp0)
					(= temp0 (temp5 nsRight?))
				)
				(if (> (temp5 nsBottom?) temp1)
					(= temp1 (temp5 nsBottom?))
				)
				(= temp3 (temp4 nextNode?))
			)
			(= castsNextNode (casts nextNode?))
		)
		(self setRect: left top (+ left temp0) (+ top temp1))
	)
	
	(method (setTitle param1 &tmp [temp0 2] temp2 newIntArray temp4 temp5 castsNextNode temp7 temp8 temp9 newDText temp11 temp12 temp13)
		(self
			addCast: (= temp2 (Cast new: name: {titleCast} yourself:))
		)
		((= newDText (DText new:))
			fore: 255
			back: 0
			font: 0
			text: param1
			posn: 0 0
		)
		(= newIntArray (IntArray new:))
		(TextSize (newIntArray data?) param1 0 -1)
		(= temp4 (+ (newIntArray at: 3) 12))
		(newDText
			nsLeft: 0
			nsTop: 0
			nsRight: (- right left)
			nsBottom: temp4
		)
		(= temp11 (- (newDText nsRight?) (newDText nsLeft?)))
		(= temp13
			(/
				(-
					temp11
					(= temp12 (- (newIntArray at: 2) (newIntArray at: 0)))
				)
				2
			)
		)
		(newDText
			textLeft: temp13
			textTop: 2
			textRight: (+ temp13 temp12 8)
			textBottom: temp4
			init: temp2
		)
		(newIntArray dispose:)
		(= temp5 (+ (- bottom top) 1))
		(if (< top temp4)
			(= top 0)
		else
			(= top (- top temp4))
		)
		(self setRect: left top right (+ top temp5 temp4) 1)
		(= castsNextNode (FirstNode (casts elements?)))
		(while castsNextNode
			(casts nextNode: (NextNode castsNextNode))
			(= temp8 (NodeValue castsNextNode))
			(= temp7 (FirstNode (temp8 elements?)))
			(while temp7
				(temp8 nextNode: (NextNode temp7))
				(if (!= (= temp9 (NodeValue temp7)) newDText)
					(temp9
						y: (+ (temp9 y?) temp4)
						nsTop: (+ (temp9 nsTop?) temp4)
						nsBottom: (+ (temp9 nsBottom?) temp4)
					)
					(UpdateScreenItem temp9)
				)
				(= temp7 (temp8 nextNode?))
			)
			(= castsNextNode (casts nextNode?))
		)
	)
)
