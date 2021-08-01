;;; Sierra Script 1.0 - (do not remove this comment)
(script# 764)
(include sci.sh)
(use Main)
(use GControl)
(use IconBar)
(use File)


(class SaveIcon_a of IconI
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor 0
		type $4000
		message -1
		modifiers $0000
		signal $0001
		helpStr 0
		maskView 1998
		maskLoop 0
		maskCel 2
		highlightColor 0
		lowlightColor 0
	)
	
	(method (show param1 param2 param3)
		(cond 
			((and (>= argc 3) param3)
				(= signal (& signal $fffb))
				(= view
					(if (self exists:)
						--UNKNOWN-PROP-NAME--
					else
						--UNKNOWN-PROP-NAME--
					)
				)
			)
			((self exists:)
				(= view --UNKNOWN-PROP-NAME--)
				(= signal (& signal $fffb))
			)
			(else
				(= view --UNKNOWN-PROP-NAME--)
				(= signal (| signal $0004))
			)
		)
		(super show: param1 param2)
	)
	
	(method (select)
		(if (super select: &rest)
			(theGame setCursor: cursor)
			(IconSave number: --UNKNOWN-PROP-NAME--)
		)
	)
	
	(method (exists &tmp [temp0 20])
		(FileIO
			10
			(Format
				@temp0
				764
				0
				curSaveDir
				(theGame name?)
				--UNKNOWN-PROP-NAME--
			)
		)
	)
)

(class IconSave of GameControls
	(properties
		elements 0
		size 0
		height 200
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		port 0
		window 0
		state $0000
		activateHeight 0
		y 0
		okButton 0
		oldCursor 0
		number 0
		gameName 0
		view 1998
		loop 12
		cel 0
		x 24
		highlightColor 0
		lowlightColor 19
	)
	
	(method (show theGameName &tmp temp0 temp1 temp2 temp3 temp4 temp5 theDiskSave)
		(if (and argc theGameName)
			(= theDiskSave diskSave)
			(= gameName theGameName)
			(= cel 0)
		else
			(= cel 1)
			(= theDiskSave diskRestore)
		)
		(= number 0)
		(= oldCursor (theGame setCursor: normalCursor))
		(self
			window:
				(systemWindow
					top: 50
					left: 110
					bottom: (+ 83 (* 5 (CelHigh view loop cel)))
					right: 210
					yourself:
				)
			add:
				game1
				game2
				game3
				game4
				game5
				game6
				game7
				game8
				game9
				game10
				theDiskSave
				trash
				cancel
			eachElementDo: #highlightColor highlightColor
			eachElementDo: #lowlightColor lowlightColor
		)
		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(= state (| state $0020))
		(window open:)
		(DrawCel view loop cel x y -1)
		(= temp4 3)
		(= temp3 14)
		(= temp0 1)
		(= temp1 (FirstNode elements))
		(while (<= temp0 10)
			(= temp2 (NodeValue temp1))
			(temp2 show: temp4 temp3 (if argc theGameName else 0))
			(= temp3 (temp2 nsBottom?))
			(if (== temp0 5)
				(= temp3 14)
				(= temp4 (+ 3 (temp2 nsRight?)))
			)
			(++ temp0)
			(= temp1 (NextNode temp1))
		)
		(theDiskSave show: (+ (temp2 nsRight?) 2) 14)
		(trash
			show: (theDiskSave nsLeft?) (+ (theDiskSave nsBottom?) 3)
		)
		(cancel show: 3 (+ (trash nsBottom?) 3))
		(theGame
			setCursor:
				theCursor
				1
				(+
					(cancel nsLeft?)
					(/ (- (cancel nsRight?) (cancel nsLeft?)) 2)
				)
				(- (cancel nsBottom?) 3)
		)
		(self doit:)
		(theGame setCursor: oldCursor)
		(self hide: dispose:)
		(= temp5 (if curIcon (curIcon number?) else -1))
		(DisposeScript 993)
		(return temp5)
	)
	
	(method (select theCurIcon param2)
		(return
			(if (theCurIcon select: (if (>= argc 2) param2))
				(if (theCurIcon respondsTo: #number)
					(= curIcon theCurIcon)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (dispatchEvent event)
		(return
			(if
				(and
					(== (event type?) evKEYBOARD)
					(== (event message?) KEY_ESCAPE)
				)
				(= curIcon (= number 0))
				(return 1)
			else
				(super dispatchEvent: event &rest)
			)
		)
	)
)

(instance game1 of SaveIcon
	(properties
		view 1998
		loop 0
		cel 0
		cursor 801
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game2 of SaveIcon
	(properties
		view 1998
		loop 1
		cel 0
		cursor 802
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game3 of SaveIcon
	(properties
		view 1998
		loop 2
		cel 0
		cursor 803
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game4 of SaveIcon
	(properties
		view 1998
		loop 3
		cel 0
		cursor 804
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game5 of SaveIcon
	(properties
		view 1998
		loop 4
		cel 0
		cursor 805
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game6 of SaveIcon
	(properties
		view 1998
		loop 5
		cel 0
		cursor 806
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game7 of SaveIcon
	(properties
		view 1998
		loop 6
		cel 0
		cursor 807
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game8 of SaveIcon
	(properties
		view 1998
		loop 7
		cel 0
		cursor 808
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game9 of SaveIcon
	(properties
		view 1998
		loop 8
		cel 0
		cursor 809
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance game10 of SaveIcon
	(properties
		view 1998
		loop 9
		cel 0
		cursor 810
		signal $0101
		maskView 1998
		maskCel 2
	)
)

(instance diskSave of IconI
	(properties
		view 1998
		loop 10
		cel 0
		signal $0141
	)
	
	(method (select)
		(if (and (super select: &rest) (IconSave number?))
			(Format
				(IconSave gameName?)
				764
				0
				curSaveDir
				(theGame name?)
				(IconSave number?)
			)
		)
	)
)

(instance diskRestore of IconI
	(properties
		view 1998
		loop 10
		cel 1
		signal $0141
	)
)

(instance trash of IconI
	(properties
		view 1998
		loop 11
		cel 0
		signal $0141
	)
	
	(method (select &tmp newClass_993_0 temp1 temp2 temp3 [temp4 20] [temp24 361] [temp385 21] temp406)
		(if (and (super select: &rest) (IconSave number?))
			((= newClass_993_0 (Class_993_0 new:))
				name: (Format @temp4 764 1 curSaveDir (theGame name?))
				open: 2
			)
			(= temp3 (GetSaveFiles (theGame name?) temp24 temp385))
			(= temp406
				(if (IsObject (IconSave curIcon?))
					((IconSave curIcon?) number?)
				else
					0
				)
			)
			(= temp1 2570)
			(= temp2 0)
			(while (< temp2 temp3)
				(if (!= temp2 temp406)
					(newClass_993_0 write: @[temp385 temp2] 2)
					(newClass_993_0 writeString: @[temp24 (* temp2 18)])
					(newClass_993_0 write: @temp1 1)
				)
				(++ temp2)
			)
			(= temp1 -1)
			(newClass_993_0 write: @temp1 2 close: dispose:)
			(Format
				@temp4
				764
				0
				curSaveDir
				(theGame name?)
				(IconSave number?)
			)
			(FileIO 4 @temp4)
			(IconSave curIcon: 0)
		)
	)
)

(class SaveIcon_b of IconI
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor 0
		type $4000
		message -1
		modifiers $0000
		signal $0001
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
	)
	
	(method (select)
		(if (super select: &rest) (theGame setCursor: cursor))
	)
)

(instance cancel of IconI
	(properties
		view 1998
		loop 13
		cel 0
		signal $0141
	)
	
	(method (select)
		(if (super select: &rest) (IconSave curIcon: 0))
	)
)
