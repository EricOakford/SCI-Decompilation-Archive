;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh)
(use Main)
(use Procs)
(use n021)
(use DText)
(use String)
(use Array)
(use Print)
(use File)
(use Game)
(use Actor)
(use System)

(public
	selectGameRoom 0
)

(local
	local0 = [91 126 161 196 231]
	[newSkeletonIcon 10]
	local15
	theValue
)
(instance selectGameRoom of Room
	(properties
		style SHOW_FADE_IN
		exitStyle 0
	)
	
	(method (init &tmp temp0 fd temp2 temp3 temp4 temp5 newDText)
		(= temp2 (String newWith: 40 {}))
		(= temp3 (IntArray newWith: 4 {}))
		(thePlane setRect: 0 0 319 199)
		(super init: &rest)
		(user canInput: TRUE)
		(theGame setCursor: normalCursor)
		(if (FileIO FileExists {kq7sg.dir})
			((= fd (File new:)) name: {kq7sg.dir} open: 1)
			(= local15 0)
			(fd read: temp2 2)
			(while (!= (temp2 at: 0) -1)
				(++ local15)
				(= temp4 (temp2 at: 0))
				(fd readString: temp2 20)
				((= [newSkeletonIcon (- local15 1)] (SkeletonIcon new:))
					view: 905
					setLoop: 3 1
					setCel: 0
					value: (- local15 1)
					fileNumber: temp4
					x: [local0 (mod (- local15 1) 5)]
					y: (if (< (- local15 1) 5) 21 else 54)
					init:
				)
				(TextSize (temp3 data?) (temp2 data?) 30 0)
				((= newDText (DText new:))
					font: 30
					text: (KString 8 (temp2 data?))
					fore: 41
					back: 0
					x:
						(-
							([newSkeletonIcon (- local15 1)] x?)
							(/ (- (temp3 at: 2) (temp3 at: 0)) 2)
						)
					y: (+ ([newSkeletonIcon (- local15 1)] y?) 13)
					setSize:
					setPri: (+ (GetHighPlanePri) 1)
					init:
				)
				([newSkeletonIcon (- local15 1)] fileName: newDText)
				(fd read: temp2 2)
			)
			(fd read: temp2 1 dispose:)
			(= temp5 (temp2 at: 0))
			(= temp0 0)
			(while (< temp0 local15)
				(if
				(== ([newSkeletonIcon temp0] fileNumber?) temp5)
					(= theValue ([newSkeletonIcon temp0] value?))
					(break)
				)
				(++ temp0)
			)
			([newSkeletonIcon theValue] select:)
		)
		(if (Btst 3)
			(selectGameControls
				add: iconDelete iconDone
				eachElementDo: #init
			)
		else
			(selectGameControls
				add: iconPlay iconDelete iconCancel
				eachElementDo: #init
			)
		)
		(if (< local15 1) (iconPlay enable: 0))
		(temp2 dispose:)
		(temp3 dispose:)
	)
)

(instance sDeleteIcon of Script
	
	(method (changeState newState &tmp theTheValue temp1 temp2 temp3 fd newFile_2)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(= temp1 (String newWith: 40 {}))
				(= temp2 (String newWith: 40 {}))
				(= temp3 (String newWith: 40 {}))
				([newSkeletonIcon theValue] eraseName:)
				((= fd (File new:))
					name:
						((temp1
							format: {kq7sg.%d} ([newSkeletonIcon theValue] fileNumber?)
						)
							data?
						)
					delete:
					dispose:
				)
				(if (!= local15 1)
					((= fd (File new:)) name: {kq7sg.dir} open: 1)
					((= newFile_2 (File new:)) name: {temp.tmp} open: 1)
					(fd read: temp1 2)
					(while (!= (proc0_3 temp1 0) -1)
						(fd readString: temp2 20)
						(if
							(!=
								(proc0_3 temp1 0)
								([newSkeletonIcon theValue] fileNumber?)
							)
							(newFile_2
								write: temp1 2
								writeString: (temp3 format: {%s\n} temp2)
							)
						)
						(fd read: temp1 2)
					)
					(fd delete: dispose:)
					(proc0_2 temp1 0 -1)
					(temp1
						at: 2 ([newSkeletonIcon (- (-- local15) 1)] fileNumber?)
					)
					(newFile_2 write: temp1 3 rename: {kq7sg.dir} dispose:)
				else
					((= fd (File new:))
						name: {kq7sg.dir}
						delete:
						dispose:
					)
					(-- local15)
				)
				([newSkeletonIcon theValue] dispose:)
				(if (and (< theValue local15) local15)
					(= theTheValue theValue)
					(while (< theTheValue local15)
						(= [newSkeletonIcon theTheValue]
							[newSkeletonIcon (+ theTheValue 1)]
						)
						([newSkeletonIcon theTheValue] value: theTheValue)
						(++ theTheValue)
					)
				)
				(= [newSkeletonIcon local15] 0)
				(if (!= local15 0)
					(= theValue (- local15 1))
				else
					(iconPlay enable: 0)
					(selectGameControls delete: iconDelete)
					(iconDelete dispose:)
					(= local15 -1)
					(= theValue -1)
				)
				(= gNewSkeletonIconFileNumber (proc21_0))
				(temp1 dispose:)
				(temp2 dispose:)
				(temp3 dispose:)
				(= ticks 60)
			)
			(2
				(iconDelete setCycle: 0 setCel: 0 enable:)
				(if (!= theValue -1)
					([newSkeletonIcon theValue] select:)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance selectGameControls of Set)

(class SelectScaryIcon of Prop
	(properties
		hiliteState 0
		enabled 1
	)
	
	(method (init)
		(self setPri: 15 ignoreActors: 1)
		(super init: &rest)
	)
	
	(method (doit)
		(if enabled
			(cond 
				((self onMe: mouseX mouseY) (if (not hiliteState) (self setCel: 3 hiliteState: 1)))
				(hiliteState (self setCel: 0 hiliteState: 0))
			)
		)
		(super doit:)
	)
	
	(method (enable param1)
		(if (and argc (not param1))
			(= enabled 0)
			(if hiliteState (self setCycle: 0 hiliteState: 0))
			(self setCel: 1)
		else
			(= enabled 1)
			(self setCel: 0)
		)
	)
)

(class SkeletonIcon of Actor
	(properties
		value 0
		fileNumber 0
		hiliteState 0
		enabled 1
		selected 0
		fileName 0
	)
	
	(method (init)
		(self setPri: 15 ignoreActors: TRUE)
		(super init: &rest)
	)
	
	(method (doit)
		(if enabled
			(cond 
				((self onMe: mouseX mouseY) (if (not hiliteState) (self setCel: 3 hiliteState: 1)))
				(hiliteState (self setCel: (if selected 1 else 0) hiliteState: 0))
			)
		)
		(super doit:)
	)
	
	(method (doVerb)
		(self select:)
	)
	
	(method (enable param1)
		(if (and argc (not param1))
			(= enabled 0)
			(if hiliteState (self setCycle: 0 hiliteState: 0))
		else
			(= enabled 1)
		)
	)
	
	(method (select)
		([newSkeletonIcon theValue] selected: 0 setCel: 0)
		(self setCel: 1 selected: 1)
		(= theValue value)
	)
	
	(method (eraseName)
		(if fileName (fileName dispose:) (= fileName 0))
	)
)

(instance iconPlay of SelectScaryIcon
	(properties
		x 161
		y 94
		view 905
	)
	
	(method (doVerb &tmp fd temp1 theGNewSkeletonIconFileNumber)
		(if enabled
			(= temp1 (String new: 2))
			(= theGNewSkeletonIconFileNumber
				gNewSkeletonIconFileNumber
			)
			(if
				(!=
					theGNewSkeletonIconFileNumber
					(= gNewSkeletonIconFileNumber
						([newSkeletonIcon theValue] fileNumber?)
					)
				)
				(temp1 at: 0 gNewSkeletonIconFileNumber)
				((= fd (File new:))
					name: {kq7sg.dir}
					open:
					seek: -1 2
					write: temp1 1
					dispose:
				)
			)
			(theGame restore: gNewSkeletonIconFileNumber)
			(temp1 dispose:)
		else
			(Prints {You must start a new game to play.})
		)
	)
)

(instance iconQuit of SelectScaryIcon
	(properties
		x 159
		y 171
		view 905
		loop 2
	)
	
	(method (doVerb)
		(= quit
			(Print
				posn: 71 82
				font: 0
				addText: {Are you sure you want to quit?} 0 0
				addButton: 1 {Yes} 127 15
				addButton: 0 {No} 168 15
				init:
			)
		)
	)
)

(instance iconDone of SelectScaryIcon
	(properties
		x 159
		y 171
		view 905
		loop 7
	)
	
	(method (doVerb)
		(curRoom newRoom: 20)
	)
)

(instance iconCancel of SelectScaryIcon
	(properties
		x 159
		y 171
		view 905
		loop 8
	)
	
	(method (doVerb)
		(curRoom newRoom: 22)
	)
)

(instance iconDelete of SelectScaryIcon
	(properties
		x 160
		y 135
		view 905
		loop 9
	)
	
	(method (doVerb &tmp temp0)
		(if
			(Print
				posn: 84 61
				font: 0
				addText: {Are you sure you want to} 0 0
				addText: {delete the selected file?} 1 10
				addButton: 1 {Yes} 89 22
				addButton: 0 {No} 132 22
				init:
			)
			(curRoom setScript: sDeleteIcon)
		)
	)
)
