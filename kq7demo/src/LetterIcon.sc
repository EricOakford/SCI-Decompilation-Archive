;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Procs)
(use n021)
(use String)
(use Array)
(use Print)
(use File)
(use Game)
(use Actor)
(use System)

(public
	nameGameRoom 0
)

(local
	letterX = [56 89 122 154 188 225 257 18 47 77 109 152 194 236 271 301 39 77 109 142 177 212 252 113 148 185]
	letterY = [68 67 68 68 68 67 68 101 101 103 101 103 101 102 103 98 139 139 141 141 139 139 137 171 171 172]
	local52 = [80 103 126 149 172 195 218 241]
	local60 = [76 98 121 144 167 190 212 236]
	local68
	[newView 8]
	local77
	[theLetter 26]
	local104
	local105
)
(instance nameGameRoom of Room
	(properties
		style SHOW_FADE_IN
		exitStyle 0
	)
	
	(method (init &tmp i)
		(thePlane setRect: 0 0 319 199)
		(super init: &rest)
		(= local77 (String newWith: 9 {}))
		(keyDownHandler add: self)
		(user canInput: TRUE)
		(theGame setCursor: normalCursor)
		(= i 0)
		(while (< i 8)
			((View new:)
				view: 907
				setLoop: 10
				x: [local52 i]
				y: 41
				init:
			)
			(++ i)
		)
		(= i 0)
		(while (< i 26)
			(alphaGameControls
				add:
					((= [theLetter i] (LetterIcon new:))
						view: (+ 906 (/ i 16))
						setLoop: (mod i 16)
						setCel: 0
						x: [letterX i]
						y: [letterY i]
						value: i
						yourself:
					)
			)
			(++ i)
		)
		(alphaGameControls add: cancelButton backspace doneButton)
		(alphaGameControls eachElementDo: #init)
	)
	
	(method (dispose)
		(local77 dispose:)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp eMsg theNewLetterIcon)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(event claimed: TRUE)
			(if
				(or
					(and
						(>= (= eMsg (event message?)) 97)
						(<= eMsg `z)
					)
					(and (>= eMsg `A) (<= eMsg `Z))
				)
				(if (and (>= eMsg `a) (<= eMsg `z))
					(-= eMsg `a)
				else
					(-= eMsg `A)
				)
				(= theNewLetterIcon [theLetter eMsg])
				(theGame
					setCursor: theCursor (theNewLetterIcon x?) (+ (theNewLetterIcon y?) 10)
				)
				(theNewLetterIcon doVerb:)
			else
				(switch eMsg
					(BACKSPACE
						(if (> local68 1) (= local104 1))
						(backspace doVerb:)
					)
					(ESC
						(cancelButton doVerb:)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		else
			(event claimed: FALSE)
		)
	)
)

(instance alphaGameControls of Set)

(instance letterCast of Cast)

(class LetterIcon of Prop
	(properties
		hiliteState 0
		enabled 1
		value 0
	)
	
	(method (init)
		(self setPri: 15 ignoreActors: TRUE)
		(super init: &rest)
		(self setHotspot: 8 10)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return FALSE))
				((and hiliteState (event type?))
					(if (== (event type?) mouseDown)
						(event type: userEvent)
					)
					(return (super handleEvent: event))
				)
				((self onMe: event)
					(if (not hiliteState)
						(self setCel: 3 hiliteState: 1)
						(UpdateScreenItem self)
					)
					(return TRUE)
				)
				(else
					(if hiliteState
						(self setCel: 0 hiliteState: 0)
						(UpdateScreenItem self)
					)
					(return FALSE)
				)
			)
		)
	)
	
	(method (doVerb)
		(if (< local68 8)
			(local77 at: local68 (+ value 65))
			((= [newView local68] (View new:))
				view: 907
				setLoop: (+ 14 (/ value 16))
				setCel: (mod value 16)
				x: [local60 local68]
				y: 36
				init:
			)
			(++ local68)
		else
			(Prints {Name too long.})
		)
	)
	
	(method (enable tOrF)
		(if (and argc (not tOrF))
			(= enabled FALSE)
			(if hiliteState
				(self hiliteState: 0)
			)
		else
			(= enabled TRUE)
		)
	)
)

(instance backspace of LetterIcon
	(properties
		x 250
		y 175
		view 907
		loop 11
	)
	
	(method (doVerb &tmp theNewLetterIcon)
		(if (> local68 0)
			(if (and (!= (-- local68) 0) local104)
				(= local104 0)
				(= theNewLetterIcon
					[theLetter (- (local77 at: (- local68 1)) 65)]
				)
				(theGame
					setCursor: theCursor (theNewLetterIcon x?) (+ (theNewLetterIcon y?) 10)
				)
			)
			([newView local68] dispose:)
			(local77 at: local68 0)
		)
	)
)

(instance doneButton of LetterIcon
	(properties
		x 291
		y 175
		view 907
		loop 12
	)
	
	(method (doVerb &tmp temp0 newFile temp2 [temp3 2])
		(= temp0 (String newWith: 20 {}))
		(if (> (local77 size:) 0)
			(= temp2 0)
			(if (FileIO FileExists {kq7sg.dir})
				((= newFile (File new:))
					name: {kq7sg.dir}
					open: 1
					read: temp0 2
				)
				(while (!= (proc0_3 temp0 0) -1)
					(newFile readString: temp0 20)
					(if (local77 compare: temp0) (= temp2 1) (break))
					(newFile read: temp0 2)
				)
				(newFile dispose:)
			)
			(if temp2
				(Prints {Your save file name must be unique.})
			else
				(= gNewSkeletonIconFileNumber (proc21_1))
				(Bset 21)
				(curRoom newRoom: 100)
			)
		else
			(Prints
				{The name of your game must consist of at least one character.}
			)
		)
		(temp0 dispose:)
	)
)

(instance cancelButton of LetterIcon
	(properties
		x 36
		y 176
		view 907
		loop 13
	)
)

(instance chooseChapter of Code
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(= temp0 (IntArray with: 0 0 0 0))
		(TextSize (temp0 data?) {A} userFont 320)
		(= temp1 (+ (temp0 at: 3) 6))
		(temp0 dispose:)
		(= curChapter
			(Print
				font: userFont
				addText: {Which chapter?} 1 (* 0 temp1)
				addButton: 1 {1} 1 (* 1 temp1)
				addButton: 2 {2} 1 (* 2 temp1)
				addButton: 3 {3} 1 (* 3 temp1)
				addButton: 4 {4} 1 (* 4 temp1)
				addButton: 5 {5} 1 (* 5 temp1)
				addButton: 6 {6} 1 (* 6 temp1)
				addButton: 7 {7} 1 (* 7 temp1)
				init:
			)
		)
	)
)
