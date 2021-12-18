;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	MESSAGER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Brian K. Hughes & Mark Wilden
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	This class controls the talkers in a game by invoking the appropriate
;;;;	one for each message.
;;;;
;;;;	Classes:
;;;;		Messager


(script# MESSAGER)

(include "64994.shm")
(include game.sh)
(use Main)
(use String)
(use Array)
(use Print)
(use System)


(class Messager kindof Object
	(properties
		caller				NULL
		disposeWhenDone	TRUE	;should we get rid of all talkers after we're done?
		oneOnly				FALSE	;only display one message
		killed				FALSE	;if we were handed an ESC or right-click by talker
		disableIconBar		TRUE	;disable the iconbar
		oldIconBarState	NULL	;state of the iconbar upon entry
		curSequence			NULL	;current sequence number
		lastSequence		NULL	;high end of sequence range
	)

;;;	(methods
;;;		cue						; Get a cue from the current talker
;;;		say						; Say a message
;;;		sayRange					; Say a range of sequences
;;;		sayNext					; Say the next message in a sequence, if any
;;;		sayFormat				; Say a formattable string (with parameters)
;;;		nextMsg					; Find the next message & call the Talker
;;;		findTalker				; Find the object ID of the talker, given define
;;;	)

	
	(method (dispose)
		;
		; Get rid of us and all those silly talkers

		(if (talkerSet size?)
			(talkerSet dispose:)
		)

		(= lastSequence 0)
		(if (and theIconBar disableIconBar)
			(theIconBar state: oldIconBarState)
			(= oldIconBarState 0)
		)

		(if caller
			(if (not cuees)
				(= cuees (Set new:))
			)
			(cuees add:
				((Cue new:)
					cuee:			caller,
					cuer:			self,
					register:	killed,
					yourself:
				)
			)
		)

		(super dispose:)
	)
	
	(method (cue killIt)
		;
		; When talker is done with message, find out if there's more

		(if (and argc killIt)
			(= killed TRUE)
		)
		(if (or oneOnly killed)
			(self dispose:)
		else
			(self nextMsg:)
		)
	)
	
	(method (say args &tmp aNoun aVerb aCase aMod)
		;
		; Set up the parameters for saying a message.

		(= aNoun (= aVerb (= aCase (= curSequence (= caller 0)))))
		(= oneOnly (= killed FALSE))
		(if (and	theIconBar
					disableIconBar
					(not oldIconBarState)
			)
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)

		; Have to pass at least the noun
		(= aNoun [args 0])

		; If we get a verb, use it
		(if (> argc 1)
			(= aVerb [args 1])
		)

		; If we get a case, use it
		(if (> argc 2)
			(= aCase [args 2])
		)

		; If we get a non-zero sequence, use it otherwise the sequence is 1
		(if (and (> argc 3) [args 3])
			(= oneOnly TRUE)
			(= curSequence [args 3])
		else
			(= curSequence 1)
		)

		; If we get a caller, use it
		(if (> argc 4)
			(= caller [args 4])
		)
		
		; If we get a module, use it otherwise the module is the curRoomNum
		(if (> argc 5)
			(= aMod [args 5])
		else
			(= aMod curRoomNum)
		)

		; Now check to see if the message is out there, and if not display
		;	an error message
		(if (not (and 	msgType
							(Message MsgGet aMod aNoun aVerb aCase curSequence)
					)
			)
			(Print
				addTextF:	{<Messager>\n
								\tmsgType set to 0 or\n
								\t%d: %d, %d, %d, %d not found}
									aMod aNoun aVerb aCase curSequence,
				init:
			)
			(= aMod GAME)
			(= aNoun N_NO_MESSAGE)
			(= aVerb (= aCase NULL))
			(= curSequence	1)
		)
		(self nextMsg: aMod aNoun aVerb aCase curSequence)
	)
	
	(method (sayRange args &tmp aNoun aVerb aCase aMod)
		;
		; Set up the parameters for saying a range of message.

		(= aNoun (= aVerb (= aCase (= curSequence (= caller 0)))))
		(= oneOnly (= killed FALSE))
		(if (and	theIconBar
					disableIconBar
					(not oldIconBarState)
			)
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)

		; Have to pass at least the noun
		(= aNoun [args 0])

		; If we get a verb, use it
		(if (> argc 1)
			(= aVerb [args 1])
		)

		; If we get a case, use it
		(if (> argc 2)
			(= aCase [args 2])
		)

		; Determine sequence range
		(= oneOnly TRUE)
		(if (and (> argc 3) [args 3])
			(= curSequence [args 3])
		else
			(= curSequence 1)
		)

		(if (and (> argc 4) [args 4])
			(= oneOnly FALSE)
			(= lastSequence [args 4])
		else
			(= lastSequence curSequence)
		)

		; If we get a caller, use it
		(if (> argc 5)
			(= caller [args 5])
		)
		
		; If we get a module, use it otherwise the module is the curRoomNum
		(if (> argc 6)
			(= aMod [args 6])
		else
			(= aMod curRoomNum)
		)

		; Now check to see if the message is out there, and if not display
		;	an error message
		(if (not (and 	msgType
							(Message MsgGet aMod aNoun aVerb aCase curSequence)
					)
			)
			(Print
				addTextF:	{<Messager>\n
								\tmsgType set to 0 or\n
								\t%d: %d, %d, %d, %d not found}
									aMod aNoun aVerb aCase curSequence,
				init:
			)
			(= aMod GAME)
			(= aNoun N_NO_MESSAGE)
			(= aVerb (= aCase NULL))
			(= curSequence	1)
		)
		(self nextMsg: aMod aNoun aVerb aCase curSequence)
	)
	
	(method (sayNext aCaller)
		;
		; Set up the parameters for saying the next message in a sequence.

		(= oneOnly (= killed FALSE))
		(= caller 0)
		(if (and	theIconBar
					disableIconBar
					(not oldIconBarState)
			)
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)

		(if argc
			(= caller aCaller)
		)

		(self nextMsg:)
	)
	
	(method (sayFormat whoCares theTalker ctrlString args &tmp len buffer aTalker)
		;
		; Say a formattable string
		; Last arg is optional caller

		(if (and	theIconBar
					disableIconBar
					(not oldIconBarState)
			)
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)

		(= aTalker (self findTalker: theTalker))
		(= len (FindFormatLen ctrlString args &rest))

		(= caller whoCares)

		(= oneOnly TRUE)
		(= buffer (String newWith: len {}))
		(buffer format: ctrlString args &rest)
		(aTalker say: buffer self)
		(buffer dispose:)
	)

	
	(method (nextMsg theMod theNoun theVerb theCase theSeq
							&tmp aTalker theBuf msgkey)
		;
		; Find the talker and the message and introduce the two of them

		(= theBuf (String newWith: 400 {}))
		(if argc
			(= aTalker (Message MsgGet theMod theNoun theVerb theCase theSeq (theBuf data?)))
		else
			(= aTalker (Message MsgNext (theBuf data?)))
		)
		(if (& msgType CD_MSG)
			(= msgkey (IntArray with: 0 0 0 0 0))
			(Message MsgGetKey (msgkey data?))
		)

		(if (and	aTalker
					(or	(not lastSequence)
							(and	lastSequence
									(<= curSequence lastSequence)
							)
					)
			)
			
			; Figure out who the talker object is
			(= aTalker (self findTalker: aTalker))

			(if (!= aTalker -1)
				; Add the talker to our list, and if the list doesn't exist create it
				(talkerSet add: aTalker)

				; Let the talker handle the message

				(if (& msgType CD_MSG)
					(aTalker
						modNum:	theMod,
						say:	theBuf self
					)
				else
					(aTalker
						modNum:	theMod,
						say:		theBuf self theMod theNoun theVerb theCase theSeq
					)
				)

				(++ curSequence)
			else
				(self dispose:)
			)
	  	else
			(self dispose:)
		)
		(if (& msgType CD_MSG)
			(msgkey dispose:)
		)
		(theBuf dispose:)
	)
	
	(method (findTalker who)
		;
		; Find the object ID of a talker, given the define from TALKERS.SH
		; NOTE: This method must be over-ridden by your instance of Messager

		(Print
			width:		200,
			addText:		{<Messager findTalker:>\n
								\tCan't find talker or\n
								\tfindTalker method not over-ridden},
			init:
		)
		(return narrator)
	)
)


(instance talkerSet of Set
	(method (dispose)
		(self
			eachElementDo: #caller:		0,
			eachElementDo: #dispose: 	(messager disposeWhenDone?),
			release:
		)
		(super dispose:)
	)
)