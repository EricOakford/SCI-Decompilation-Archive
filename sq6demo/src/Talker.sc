;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	TALKER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Brian K. Hughes & Mark Wilden (original by Mark Hood)
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	Classes:
;;;;		Blink
;;;;		Narrator
;;;;		Talker


(script# TALKER)
(include game.sh)
(use Main)
(use String)
(use Print)
(use Sync)
(use RandCyc)
(use Motion)
(use Actor)
(use System)


(class Blink kindof Cycle
	(properties
		cycleDir		1
		waitCount	0
		lastCount	0
		waitMin		0
		waitMax		0
	)
	(method (init obj theTime)
		(if argc
			(= waitMin (/ theTime 2))
			(= waitMax (+ theTime waitMin))
	 		(super init: obj)
		else
			(super init:)
		)
	)


	(method (doit &tmp newCel)
		(if waitCount
			(if (> (- gameTime waitCount) 0)
				(= waitCount 0)
				(= cycleCnt gameTime) ; wait before starting blink
			)
		else
			(= newCel (self nextCel:))
			(if (!= newCel (client cel?) )
				(if
					(or 
						(> newCel clientLastCel)
						(< newCel 0)
					)
					(= cycleDir (- cycleDir))
					(self cycleDone:)
				else
					(client cel: newCel)
				)
			)
		)
	)
	(method (cycleDone)
		(if (== cycleDir -1)
			(= cycleCnt gameTime) ; wait before opening eyes
		else
			(= waitCount (+ (Random waitMin waitMax) gameTime))
		)
	)	
)


(class Narrator kindof Object
	(properties
		x						-1			;-Narrator will center his messages
		y						-1			;/
		caller				NULL
		modNum				-1			;Module number of message file
		disposeWhenDone	TRUE		;Dispose & hide or just dispose
		ticks					NULL
		talkWidth			NULL		;Width of text box
		modeless				DLG_SEMI_MODAL
											; Modal    - require keypress to clear text box
											; Semi     - talker locked in loop with other
											;					talkers until cleared or timed out
											; modeless - normal game operation during talker
		font					0
		cueVal				FALSE		;Return TRUE if right-click or ESC
		initialized			FALSE		;True if we are already inited
		showTitle			FALSE		;TRUE if name should be displayed as title
		fore					0			;Foreground color of text
		back					7			;Background color of plane

		dialog				NULL		;handle to Dialog

		curVolume			NULL		;Private, for CD use
		saveCursor			NULL		;Private, for CD use
		audModNum			NULL
		audNoun			   NULL
		audVerb			   NULL
		audCase			   NULL
		audSequence		   NULL
		;EO: LSL6/SQ6-specific properties
		strHandle 0
		curText 0
	)
	
;;;	(methods
;;;		init					;Initialize and adjust volume if necessary
;;;		handleEvent			;Handle events from the user
;;;		dispose				;Delete from fastCast and optionally cue caller
;;;		say					;startText or startAudio based on msgType
;;;		startText			;Prepare message
;;;		display				;Print message
;;;		startAudio			;Start talker sync'ing to audio
;;;		doit					;A fastCast animation cycle
;;;		isModeless			;Return TRUE of modeless state matches arg
;;;	)

	(method (init)
		(if (& msgType CD_MSG)
			; Lower the volume of the game music (if any)
			(= curVolume (theGame masterVolume?))
			(if (>= curVolume 6)
				(theGame masterVolume:	(- curVolume 6))
			else
				(theGame masterVolume:	1)
			)
		)
		(= initialized TRUE)
		(super init:)
		(talkers addToFront: self)
	)
	
	(method (handleEvent event &tmp eType)
		(cond
			((event claimed?))
			((== ticks -1)
				(return FALSE)
			)
			(else
				(= eType (event type?))
				(if (not cueVal)
					(cond
						((& eType joyDown)
							(= cueVal FALSE)
						)
						((& eType mouseDown)
							(= cueVal (& (event modifiers?) shiftDown))
						)
						((& eType userEvent)
							(= cueVal (& (event modifiers?) shiftDown))
							; Since dialogs don't look at userEvents...
							(event
								type: 		mouseDown,
								message:		0,
								modifiers:	0
							)
						)
						((& eType keyDown)
							(= cueVal (== (event message?) ESC))
						)
					)
				(if (or
						(& eType (| userEvent joyDown mouseDown))
						(and
							(& eType keyDown)
							(OneOf (event message?) ENTER ESC)
						)
					)
						(if (& msgType TEXT_MSG) ;(and	(not (& msgType CD_MSG))
							(dialog handleEvent: event)
						)
						(event claimed: TRUE)
						(self dispose: disposeWhenDone)
					)
				)
			)
		)
	)
	
	(method (say theBuf whoCares theMod theNoun theVerb theCase theSeq)
		;
		; Set up any properties required to say this message & pass the buffer
		;	on to the startText and/or startAudio methods.  The message params
		;	are passed purely for audio's sake
		;
		; 'theBuf' is a string object.

		; If we have not been inited, init the talker
      (if (not initialized)
			(self init:)
		)

		; See if we have a caller
		(= caller
			(if (and (> argc 1) whoCares)
				whoCares
			else
				0
			)
		)

		; Figure out what to do with it
		(if (& msgType TEXT_MSG)
			(self startText: theBuf)
		)
		(if (& msgType CD_MSG)
			(self startAudio: theMod theNoun theVerb theCase theSeq)
		)
		(= ticks (+ ticks 60 gameTime))
		(return TRUE)
	)
	
	(method (startText theBuf &tmp strLength)
		;
		; If there is a CD component to this message as well, get ticks from
		;	there, else get 'em from here.  Dialog time is calculated as:
		;
		;		2.4 ticks * msg length * textSpeed
		;
		;	Thus, a message of 80 characters would take 2.4 * 80 * 5 = 960 ticks
		;	at a text speed of 5, or 16 seconds.  We return the ticks value so
		;	that others can use it.
		;
		; 'theBuf' is a string object.

		(if (not (& msgType CD_MSG))
			(= strLength (theBuf size:))
			(= ticks (Max 	120 ; keep plane up at least 4 seconds
								(* (/ (* 24 strLength) 10) textSpeed)
						)
			)
		)

		(self display: theBuf)
		(return ticks)
	)
	
   (method (display theBuf &tmp theWidth thePrint)

		; Open a plane & display the text
		;
		; 'theBuf' is a string object.

		((= thePrint (Print new:))
			x: 7
			y: 192
			font: userFont
			back: 0
			modeless: DLG_MODELESS
			init:
		)
		(if showTitle
			(= strHandle (String format: {%s\n\n} theBuf))
			(= curText
				(showTitle addString: (strHandle data?) userFont 82 0)
			)
		)
		(prints delete: thePrint)
		(= dialog (thePrint dialog?))
	)
	
	(method (startAudio m n v c s)
		(= audModNum m)
		(= audNoun n)
		(= audVerb v)
		(= audCase c)
		(= ticks
			(DoAudio
				2
				m
				n
				v
				c
				(= audSequence s)
			)
		)
	)
	
	(method (doit)

		; Do real-time ticks stuff & see if we need to dispose yet or not

		; If we're out of ticks (and DoAudio AudLoc is -1 IF CD)
		(if (!= ticks -1)
			(if (and	(> (- gameTime ticks) 0)
						(if (& msgType CD_MSG)
							(== 
								(DoAudio AudLoc audModNum audNoun audVerb audCase audSequence) 
								-1
							)
						else
							TRUE
						)
					)
				(if (or 	(!= modeless DLG_MODAL)
							(& msgType CD_MSG)
					)
   				(self dispose: disposeWhenDone)
        			(return FALSE)
         	)
			)
		)
		(return TRUE)
	)
	
	(method (dispose dWD)
		;EO: LSL6/SQ6 specific
		(if curText
			(showTitle addString: {} userFont 82 0)
			(strHandle dispose:)
			(= curText 0)
		)
		; This is so we can still get doits but not try to mess with gameTime
		(= ticks -1)

		; If disposeWhenDone was passed and is TRUE
		;		- OR -
		;	nothing was passed, do the full dispose

		(if (or (not argc) dWD)
			(= modNum -1)
			(= initialized FALSE)
			(talkers delete: self)
		)
		(if dialog
			(dialog dispose:)
			(= dialog 0)
		)

		(if (& msgType CD_MSG)
			(DoAudio AudStop audModNum audNoun audVerb audCase audSequence)
			(theGame masterMusicVolume: curVolume)
		)
		; If we are supposed to cue someone, do so & pass along the cueVal,
		;	which indicates whether we hit an ESC or right-click.  Whether
		;	or not the caller cares is entirely up to it.
		(if caller
			(caller cue: cueVal)
		)

		(= cueVal NULL)

		(if (or (not argc) dWD)
			(super dispose:)
		)
	)
	
	(method (isModeless checkFor)
		;
		; If a parameter is passed, return whether that parameter matches
		;	our modeless state.  Else, return the modeless state, which will be:
		;		0 - Truly modal, no game execution until talker disposed
		;		1 - Semi-modal, all talkers in talkers list execute
		;		2 - Truly modeless, game execution continues (if possible)

		(return
			(if argc
				(== modeless checkFor)
			else
				modeless
			)
		)
	)
)

(class Talker kindof Narrator
	(properties
		frame					NULL
		bust					NULL
		eyes					NULL
		mouth					NULL
		framePri 197
		bustPri 198
		eyePri 199
		mouthPri 200
		view 0
		loop 0
		cel 0
		priority 199
		viewInPrint			FALSE
		textX					0			;-top left of text box, relative to talker
		textY					0			;/
		talkWidth			318
		blinkSpeed			100		;# ticks to wait before blinking again,
											;	randomized from 50% to 150%

	)
	
;;;	(methods 
;;;		hide				;erase talker from screen
;;;	)

	(method (init theMouth theBust theEyes theFrame)
		; Set up parameters (if any)
		(if argc
			(= mouth theMouth)
			(if (> argc 1)
				(= bust theBust)
				(if (> argc 2)
					(= eyes theEyes)
					(if (> argc 3)
						(= frame theFrame)
					)
				)
			)
		)

		(super init:)

		; If they didn't supply us with a mouth, make one of our own
		(if (not mouth)
			(= mouth
				((Prop new:)
					view:			view,
					loop:			loop,
					cel:			cel,
					x:				x,
					y:				y,
					yourself:
				)
			)
		)
		(mouth
			init:		,
			setPri:	priority
		)

		(if bust
			(bust
				setPri:	priority,
				init:
			)
		)
		(if eyes
			(eyes
				setPri:	priority,
				init:
			)
		)
		(if frame
			(frame
				setPri:	priority,
				init:
			)
		)
	)
	
	(method (startText &tmp theTicks)
		(= theTicks (super startText: &rest))

		; The time of the mouth movement is 2/3 the total time of the msg
		(if mouth
			(mouth setCycle: RandCycle (* (/ theTicks 3) 2) NULL TRUE)
		)
		(if (and	eyes
					(not (eyes cycler?))
				)
			(eyes setCycle: Blink blinkSpeed)
		)
	)
	
   (method (display theBuf &tmp theLoop theWidth textLeft theObj thePrint)

		; Display the text, with an icon for the talker if
		;	it is a 'viewInPrint' talker.
		;
		; 'theBuf' is a string object.

		(= theObj
			(cond
				(frame	frame)
				(bust		bust)
				(else		mouth)
			)
		)

		(= thePrint (Print new:))

      (if viewInPrint
			(= theLoop (theObj loop?))

			(if showTitle
				(thePrint addTitle: name)
			)
			(thePrint
				x: 7
				y: 192
				font: userFont
				back: 0
				modeless: DLG_MODELESS
				init:
			)
      else
			; If we didn't specify textX and textY, try to place to the right
			;	of the talker's portrait
			(if (not (+ textX textY))
				(= textX (+ (CelWide (theObj view?) (theObj loop?) (theObj cel?)) 5))
			)

			; Check to see if the text width would be off the screen
			(= textLeft (+ (theObj nsLeft?) textX))
			(if (> (+ textLeft talkWidth) 318)		; stop at right edge
				(= theWidth (- 318 textLeft))
			else
				(= theWidth talkWidth)
			)
			(if showTitle
				(thePrint addTitle: name)
			)
			(thePrint
				x: 7
				y: 192
				font: userFont
				back: 0
				modeless: DLG_MODELESS
				init:
			)
		)
		(if showTitle
			(= strHandle (String format: {%s\n} (self name?)))
			(showTitle addString: (strHandle data?) 50 84 0)
			(strHandle dispose:)
			(= strHandle (String format: {%s\n\n} theBuf))
			(= curText
				(showTitle addString: (strHandle data?) userFont 82 0 0)
			)
		)
		(prints delete: thePrint)
		(= dialog (thePrint dialog?))
	)
	
	(method (startAudio theKeys &tmp m n v c s)
		(= audModNum m)
		(= audNoun n)
		(= audVerb v)
		(= audCase c)
		(= audSequence s)
		(super startAudio: theKeys)
		
		(if mouth
			(= m (theKeys at: 0))
			(= n (theKeys at: 1))
			(= v (theKeys at: 2))
			(= c (theKeys at: 3))
			(= s (theKeys at: 4))
			(mouth setCycle: MouthSync m n v c s)
		)
		(if (and	eyes
					(not (eyes cycler?))
				)
		  	(eyes	setCycle: Blink blinkSpeed)
		)
	)

	(method (dispose dWD)
		;EO: LSL6/SQ6 specific
		(if curText
			(showTitle addString: {} 50 84 0)
			(showTitle addString: {} userFont 82 0)
			(strHandle dispose:)
			(= curText 0)
		)
		
		; We want to remove the mouth synch because the CD guys say we
		;	can really only have one at a time & another talker can't use
		;	it unless we give it up

		(if mouth
			(mouth
				setCycle: 	0,
				setCel:		0
			)
		)

		(if (or (not argc) dWD)
			(if eyes
				(eyes
					setCycle:	0,
					setCel:		0
				)
			)

			(if mouth
				(mouth dispose:)
	  			(= mouth 0)
			)
			(if bust
				(bust dispose:)
	  			(= bust 0)
			)
			(if eyes
				(eyes dispose:)
	  			(= eyes 0)
			)
			(if frame
				(frame dispose:)
	  			(= frame 0)
			)
		)
		(super dispose: dWD)
	)
)
